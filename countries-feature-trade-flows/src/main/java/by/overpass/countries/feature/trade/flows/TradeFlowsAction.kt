package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.Action

/**
 * Trade Flows Actions
 */
sealed class TradeFlowsAction : Action {

    object LoadExports : TradeFlowsAction()

    /**
     * @property exportsString the list of exports to be shown
     */
    data class ShowExports(
        val exportsString: String
    ) : TradeFlowsAction()
}