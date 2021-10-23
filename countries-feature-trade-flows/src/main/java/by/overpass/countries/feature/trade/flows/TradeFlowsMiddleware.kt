package by.overpass.countries.feature.trade.flows

import by.overpass.countries.data.OecApi
import by.overpass.countries.redux.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TradeFlowsMiddleware(
    private val oecApi: OecApi,
    private val countryId: String,
) : Middleware<TradeFlowsState, TradeFlowsAction> {

    override suspend fun processAction(
        state: TradeFlowsState,
        action: TradeFlowsAction,
    ): TradeFlowsAction = when (action) {
        is TradeFlowsAction.LoadExports ->
            withContext(Dispatchers.Default) {
                oecApi.getHs92TradeFlows(countryId)
                    .data
                    .toString()
                    .let { TradeFlowsAction.ShowExports(it) }
            }
        is TradeFlowsAction.ShowExports -> action.copy()
    }
}