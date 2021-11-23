package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.graphics.Color
import by.overpass.treemapchart.core.tree.tree
import org.junit.Assert.assertEquals
import org.junit.Test

class TradeFlowsReducerTest {

    private val reducer = TradeFlowsReducer()

    private val countryName = "countryName"
    private val exportsTree = tree(
        UiExport(
            5.0,
            Color.Black,
            "name"
        )
    ) {
        UiExport(
            5.0,
            Color.Black,
            "name"
        )
    }

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
            TradeFlowsState.ExportsLoaded(countryName, exportsTree),
            reducer.reduce(
                TradeFlowsState.Loading,
                TradeFlowsAction.ShowExportsChartAndCountryName(countryName, exportsTree)
            )
        )
    }
}