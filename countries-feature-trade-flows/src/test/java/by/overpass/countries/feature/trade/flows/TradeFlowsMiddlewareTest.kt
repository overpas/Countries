/**
 * Trade Flows Middleware tests
 */

@file:Suppress("FILE_UNORDERED_IMPORTS")

package by.overpass.countries.feature.trade.flows

import by.overpass.countries.data.MockOecApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

private const val EXPECTED_EXPORTS_STRING = "[" +
        "Hs92ProductExportsImports(" +
        "exportRca=2.284," +
        " exportVal=2.0141394708E8," +
        " exportValGrowthPct=-0.071," +
        " exportValGrowthPct5=0.001," +
        " exportValGrowthVal=-1.548557307E7," +
        " exportValGrowthVal5=1013855.32," +
        " hs92Id=01010111," +
        " hs92IdLen=8.0," +
        " importRca=0.181," +
        " importVal=2.491219367E7," +
        " importValGrowthPct=-0.387," +
        " importValGrowthPct5=-0.107," +
        " importValGrowthVal=-1.570402659E7," +
        " importValGrowthVal5=-1.895060004E7," +
        " originId=nausa," +
        " destinationId=all," +
        " year=2010.0" +
        "), " +
        "Hs92ProductExportsImports(" +
        "exportRca=2.393," +
        " exportVal=1.8929173077E8," +
        " exportValGrowthPct=0.039," +
        " exportValGrowthPct5=0.108," +
        " exportValGrowthVal=7049210.7," +
        " exportValGrowthVal5=7.579246876E7," +
        " hs92Id=01010119," +
        " hs92IdLen=8.0," +
        " importRca=1.819," +
        " importVal=2.2521425339E8," +
        " importValGrowthPct=0.111," +
        " importValGrowthPct5=-0.038," +
        " importValGrowthVal=2.256279593E7," +
        " importValGrowthVal5=-4.809334035E7," +
        " originId=nausa," +
        " destinationId=all," +
        " year=2010.0" +
        ")" +
        "]"

class TradeFlowsMiddlewareTest {

    private val middleware = TradeFlowsMiddleware(MockOecApi(), "countryId")

    @Test
    fun `load exports - show exports`() = runBlocking {
        assertEquals(
            TradeFlowsAction.ShowExports(EXPECTED_EXPORTS_STRING),
            middleware.processAction(TradeFlowsState.Loading, TradeFlowsAction.LoadExports)
        )
    }

    @Test
    fun `show exports - show exports`() = runBlocking {
        assertEquals(
            TradeFlowsAction.ShowExports(EXPECTED_EXPORTS_STRING),
            middleware.processAction(
                TradeFlowsState.Loading,
                TradeFlowsAction.ShowExports(EXPECTED_EXPORTS_STRING)
            )
        )
    }
}