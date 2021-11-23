package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ExportsTreeMiddleware(
    private val exportsTreeUseCase: ExportsTreeUseCase,
    private val countryId: String,
) : Middleware<TradeFlowsState, TradeFlowsAction> {

    override suspend fun processAction(
        state: TradeFlowsState,
        action: TradeFlowsAction,
    ): Flow<TradeFlowsAction> {
        return when (action) {
            is TradeFlowsAction.LoadExports -> exportsTreeUseCase.createExportsTree(countryId)
                .map { exportsTreeState ->
                    when (exportsTreeState) {
                        is ExportsTreeState.Loaded ->
                            TradeFlowsAction.ShowExportsChart(exportsTreeState.exports)
                        is ExportsTreeState.LoadingExportsData ->
                            TradeFlowsAction.LoadPartOfExports("Loading exports...")
                        is ExportsTreeState.LoadingProductsData ->
                            TradeFlowsAction.LoadPartOfExports("Loading products...")
                        is ExportsTreeState.VisualizingExportsChart ->
                            TradeFlowsAction.LoadPartOfExports("Visualizing treemap...")
                    }
                }
            else -> flowOf(action)
        }
    }
}