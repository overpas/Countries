package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.State

sealed class TradeFlowsState : State {

    object Loading : TradeFlowsState()

    /**
     * @property exportsString the exports to be displayed
     */
    data class ExportsLoaded(
        val exportsString: String
    ) : TradeFlowsState()

    /**
     * @property message the message of the error
     */
    data class Error(
        val message: String
    ) : TradeFlowsState()
}