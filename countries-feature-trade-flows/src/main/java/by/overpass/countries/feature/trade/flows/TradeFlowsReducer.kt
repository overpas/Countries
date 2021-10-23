package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.Reducer

class TradeFlowsReducer : Reducer<TradeFlowsState, TradeFlowsAction> {

    override fun reduce(state: TradeFlowsState, action: TradeFlowsAction): TradeFlowsState =
        when (action) {
            is TradeFlowsAction.LoadExports -> TradeFlowsState.Loading
            is TradeFlowsAction.ShowExports -> TradeFlowsState.ExportsLoaded(action.exportsString)
        }
}