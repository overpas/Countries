package by.overpass.countries.feature.trade.flows

import by.overpass.countries.data.OecApi
import by.overpass.countries.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CountryExportsMiddleware(
    private val oecApi: OecApi,
    private val countryId: String,
) : Middleware<TradeFlowsState, TradeFlowsAction> {

    override suspend fun processAction(
        state: TradeFlowsState,
        action: TradeFlowsAction,
    ): Flow<TradeFlowsAction> = when (action) {
        is TradeFlowsAction.ShowExportsChart -> {
            flowOf(action).map {
                val countryName = oecApi.getCountries()
                    .countries
                    .find { it.displayId == countryId }
                    ?.name
                countryName?.let {
                    TradeFlowsAction.ShowExportsChartAndCountryName(it, action.exports)
                } ?: TradeFlowsAction.ShowCountryNotFoundError
            }
        }
        else -> flowOf(action)
    }
}
