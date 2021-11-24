/**
 * Exports treemap creation logic
 */

@file:Suppress("MagicNumber")

package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.graphics.Color
import by.overpass.countries.data.HS92_ID_LENGTH_6
import by.overpass.countries.data.OecApi
import by.overpass.countries.ui.common.theme.rgbColor
import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.core.tree.tree
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private val mockExportsTree = tree(
    UiExport(2.0, Color.Black, "1")
) {
    node(UiExport(1.0, Color.Black, "2"))
    node(UiExport(1.0, Color.Black, "3"))
}

typealias ExportsCategories = List<Pair<UiExportCategory, List<UiExport>>>

interface ExportsTreeUseCase {

    fun createExportsTree(countryId: String): Flow<ExportsTreeState>
}

sealed class ExportsTreeState {

    object LoadingExportsData : ExportsTreeState()

    object LoadingProductsData : ExportsTreeState()

    object VisualizingExportsChart : ExportsTreeState()

    /**
     * @property exports a tree representing exports
     */
    data class Loaded(
        val exports: Tree<UiExport>,
    ) : ExportsTreeState()
}

class ExportsTreeOecUseCase(
    private val oecApi: OecApi,
) : ExportsTreeUseCase {

    override fun createExportsTree(countryId: String): Flow<ExportsTreeState> = flow {
        emit(ExportsTreeState.LoadingExportsData)
        val exports = oecApi.getHs92TradeFlows(countryId)
            .data
            .filter { it.hs92IdLen == HS92_ID_LENGTH_6 && it.exportVal != null }
        emit(ExportsTreeState.LoadingProductsData)
        val products = oecApi.getProducts().data
        emit(ExportsTreeState.VisualizingExportsChart)
        val visibleExports: MutableList<UiExport> = mutableListOf()
        exports.forEach { export ->
            products.find { product -> product.id == export.hs92Id }
                ?.let { product ->
                    visibleExports += UiExport(
                        export.exportVal!!,
                        product.color.rgbColor,
                        product.name,
                        product.category,
                    )
                }
        }
        val categories = visibleExports.groupBy { it.category ?: UiExportCategory.SCOTLAM }
            .entries
            .map { (category, exports) ->
                category to exports.separateInsignificant(
                    0.7,
                    { it.value },
                    { items ->
                        UiExport(
                            items.sumOf { it.value },
                            items.first().color,
                            "Other",
                            items.first().category,
                        )
                    },
                )
            }
            .sortedByDescending { (category, exports) -> exports.sumOf { export -> export.value } }
        emit(
            ExportsTreeState.Loaded(
                createExportsTree(
                    visibleExports.sumOf { it.value },
                    categories,
                )
            )
        )
    }

    private fun createExportsTree(totalSum: Double, categories: ExportsCategories) = tree(
        UiExport(
            totalSum,
            Color.White,
            "All"
        )
    ) {
        categories.forEach { (category, exports) ->
            node(
                UiExport(
                    exports.sumOf { it.value },
                    Color.White,
                    category.name,
                    category,
                )
            ) {
                exports.forEach { export ->
                    node(
                        UiExport(
                            export.value,
                            export.color,
                            export.name,
                            export.category,
                        ),
                    )
                }
            }
        }
    }
}

class MockExportsTreeUseCase(
    private val exportsTree: Tree<UiExport> = mockExportsTree,
) : ExportsTreeUseCase {
    override fun createExportsTree(countryId: String): Flow<ExportsTreeState> = flow {
        emit(ExportsTreeState.LoadingExportsData)
        emit(ExportsTreeState.LoadingProductsData)
        emit(ExportsTreeState.VisualizingExportsChart)
        emit(ExportsTreeState.Loaded(exportsTree))
    }
}

@Suppress("FLOAT_IN_ACCURATE_CALCULATIONS")
fun <T> List<T>.separateInsignificant(
    mainPercentage: Double,
    evaluator: (T) -> Double,
    groupInsignificant: (List<T>) -> T,
): List<T> {
    val sorted = sortedByDescending(evaluator)
    val total = sorted.sumOf(evaluator)
    val mainItems: MutableList<T> = mutableListOf()
    val mainMax = mainPercentage * total
    var mainTotal = 0.0
    val insignificantItems: MutableList<T> = mutableListOf()
    sorted.forEach {
        if (mainTotal < mainMax) {
            mainTotal += evaluator(it)
            mainItems += it
        } else {
            insignificantItems += it
        }
    }
    return mainItems + groupInsignificant(insignificantItems)
}