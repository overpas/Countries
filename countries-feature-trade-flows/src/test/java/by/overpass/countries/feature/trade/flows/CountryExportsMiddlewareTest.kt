/**
 * Trade Flows Middleware tests
 */

@file:Suppress("FILE_UNORDERED_IMPORTS")

package by.overpass.countries.feature.trade.flows

import androidx.compose.ui.graphics.Color
import by.overpass.countries.data.MockOecApi
import by.overpass.treemapchart.core.tree.tree
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryExportsMiddlewareTest {

    private val countryName = "Libya"
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

    private val middleware = CountryExportsMiddleware(MockOecApi(), "lby")

    @Test
    fun `show exports chart - show exports chart and country name`() = runBlockingTest {
        val processAction = middleware.processAction(
            TradeFlowsState.Loading,
            TradeFlowsAction.ShowExportsChart(exportsTree),
        ).first()
        assertEquals(
            TradeFlowsAction.ShowExportsChartAndCountryName(countryName, exportsTree),
            processAction
        )
    }

    @Test
    fun `show exports chart - show exports chart and country name (country not found)`() =
        runBlockingTest {
            val countryExportsMiddleware = CountryExportsMiddleware(MockOecApi(), "aaa")
            val processAction = countryExportsMiddleware.processAction(
                TradeFlowsState.Loading,
                TradeFlowsAction.ShowExportsChart(exportsTree),
            ).first()
            assertEquals(TradeFlowsAction.ShowCountryNotFoundError, processAction)
        }

    @Test
    fun `action not changed`() = runBlockingTest {
        val processAction = middleware.processAction(
            TradeFlowsState.Loading,
            TradeFlowsAction.ShowCountryNotFoundError,
        ).first()
        assertEquals(TradeFlowsAction.ShowCountryNotFoundError, processAction)
    }
}