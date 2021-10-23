package by.overpass.countries.feature.trade.flows

import org.junit.Assert.assertEquals
import org.junit.Test

class TradeFlowsReducerTest {

    private val reducer = TradeFlowsReducer()

    @Test
    fun `load exports - state is loading`() {
        assertEquals(
            TradeFlowsState.Loading,
            reducer.reduce(TradeFlowsState.Loading, TradeFlowsAction.LoadExports)
        )
    }

    @Test
    fun `show exports - state is loaded`() {
        assertEquals(
            TradeFlowsState.ExportsLoaded("exports"),
            reducer.reduce(
                TradeFlowsState.Loading,
                TradeFlowsAction.ShowExports("exports")
            )
        )
    }
}