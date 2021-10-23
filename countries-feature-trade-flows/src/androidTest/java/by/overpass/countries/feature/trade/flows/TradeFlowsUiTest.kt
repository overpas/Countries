package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import by.overpass.countries.data.MockOecApi
import by.overpass.countries.data.RealNetworkComponent
import by.overpass.countries.redux.android.store
import by.overpass.countries.redux.reducer
import org.junit.Rule
import org.junit.Test

class TradeFlowsUiTest {

    private val tradeFlowsComponent = RealTradeFlowsComponent(RealNetworkComponent, "countryId")
    private val mockApi = MockOecApi()
    private val middleware = tradeFlowsComponent.tradeFlowsMiddleware(mockApi)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoading() {
        composeTestRule.setContent {
            TradeFlows(
                store {
                    tradeFlowsComponent.tradeFlowsStore(
                        middleware = middleware,
                        reducer = reducer { state, action ->
                            TradeFlowsState.Loading
                        },
                    )
                }
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()
    }

    @Test
    fun testExportsShown() {
        composeTestRule.setContent {
            TradeFlows(
                store {
                    tradeFlowsComponent.tradeFlowsStore(
                        middleware = middleware,
                        reducer = reducer { state, action ->
                            TradeFlowsState.ExportsLoaded("exportsString")
                        },
                    )
                }
            )
        }

        composeTestRule
            .onNodeWithText("exportsString")
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
                }
            )
        }
    }
}