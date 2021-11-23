package by.overpass.countries.feature.trade.flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class ExportsTreeMiddlewareTest {

    private val exportsTreeMiddleware = ExportsTreeMiddleware(
        MockExportsTreeUseCase(),
        "countryId",
    )

    @Test
    fun `load exports loads exports, products, creates the treemap and shows exports`() =
        runBlockingTest {
            val actions = exportsTreeMiddleware.processAction(
                TradeFlowsState.Loading,
                TradeFlowsAction.LoadExports
            ).toList()
            assertEquals(TradeFlowsAction.LoadPartOfExports("Loading exports..."), actions[0])
            assertEquals(TradeFlowsAction.LoadPartOfExports("Loading products..."), actions[1])
            assertEquals(TradeFlowsAction.LoadPartOfExports("Visualizing treemap..."), actions[2])
            assertTrue(actions[3] is TradeFlowsAction.ShowExportsChart)
        }
}