package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.State
import by.overpass.treemapchart.core.tree.Tree

sealed class TradeFlowsState : State {

    object Loading : TradeFlowsState()

    /**
     * @property exports the exports to be displayed
     */
    data class ExportsLoaded(
        val countryName: String,
        val exports: Tree<UiExport>,
    ) : TradeFlowsState()

    /**
     * @property message the message of the error
     */
    data class Error(
        val message: String,
    ) : TradeFlowsState()

    data class LoadingPartOfExports(val message: String) : TradeFlowsState()
}
