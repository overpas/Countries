package by.overpass.countries.feature.trade.flows

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.data.MockOecApi
import by.overpass.countries.data.RealNetworkComponent
import by.overpass.countries.redux.android.store
import by.overpass.countries.redux.reducer
import by.overpass.treemapchart.core.tree.tree
import org.junit.Rule
import org.junit.Test

class TradeFlowsUiTest {

    private val tradeFlowsComponent = RealTradeFlowsComponent(RealNetworkComponent, "countryId")
    private val mockApi = MockOecApi()
    private val exportsTree = tree(
        UiExport(2.0, Color.Black, "1")
    ) {
        node(UiExport(1.0, Color.Black, "2"))
        node(UiExport(1.0, Color.Black, "3"))
    }
    private val mockExportsTreeUseCase = MockExportsTreeUseCase(exportsTree)
    private val middleware = tradeFlowsComponent.tradeFlowsMiddleware(
        oecApi = mockApi,
        exportsTreeUseCase = mockExportsTreeUseCase
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingExports() {
        composeTestRule.setContent {
            TradeFlows(
                store {
                    tradeFlowsComponent.tradeFlowsStore(
                        middleware = middleware,
                        reducer = reducer { state, action ->
                            TradeFlowsState.LoadingPartOfExports("Loading exports...")
                        },
                    )
                },
                rememberNavController(),
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()

        composeTestRule
            .onNodeWithText("Loading exports...")
            .assertExists()
    }

    @Test
    fun testExportsTreemapAndCountryNameShown() {
        composeTestRule.setContent {
            TradeFlows(
                store {
                    tradeFlowsComponent.tradeFlowsStore(
                        middleware = middleware,
                        reducer = reducer { state, action ->
                            TradeFlowsState.ExportsLoaded("countryName", exportsTree)
                        },
                    )
                },
                rememberNavController(),
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        }

        composeTestRule
            .onNodeWithText("countryName > Exports")
            .assertExists()

        composeTestRule
            .onNodeWithText("2")
            .assertExists()

        composeTestRule
            .onNodeWithText("3")
            .assertExists()
    }

    @Test(expected = NotImplementedError::class)
    fun testError() {
        composeTestRule.setContent {
            TradeFlows(
                store {
                    tradeFlowsComponent.tradeFlowsStore(
                        middleware = middleware,
                        reducer = reducer { state, action ->
                            TradeFlowsState.Error("error")
                        },
                    )
                },
                rememberNavController(),
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        }
    }
}