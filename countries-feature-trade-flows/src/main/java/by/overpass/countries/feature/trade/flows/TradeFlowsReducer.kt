package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.Reducer

class TradeFlowsReducer : Reducer<TradeFlowsState, TradeFlowsAction> {

    override fun reduce(state: TradeFlowsState, action: TradeFlowsAction): TradeFlowsState =
        when (action) {
            is TradeFlowsAction.LoadExports -> TradeFlowsState.Loading
            is TradeFlowsAction.ShowExportsChartAndCountryName -> TradeFlowsState.ExportsLoaded(
                action.countryName,
                action.exports,
            )
            is TradeFlowsAction.ShowCountryNotFoundError ->
                TradeFlowsState.Error("Country not found")
            is TradeFlowsAction.LoadPartOfExports ->
                TradeFlowsState.LoadingPartOfExports(action.message)
            else -> state
        }
}