package by.overpass.countries.feature.trade.flows

import by.overpass.countries.redux.Action
import by.overpass.treemapchart.core.tree.Tree

/**
 * Trade Flows Actions
 */
sealed class TradeFlowsAction : Action {

    object LoadExports : TradeFlowsAction()

    object ShowCountryNotFoundError : TradeFlowsAction()

    /**
     * @property exports the list of exports to be shown
     */
    data class ShowExportsChartAndCountryName(
        val countryName: String,
        val exports: Tree<UiExport>,
    ) : TradeFlowsAction()

    data class ShowExportsChart(
        val exports: Tree<UiExport>,
    ) : TradeFlowsAction()

    data class LoadPartOfExports(val message: String) : TradeFlowsAction()
}