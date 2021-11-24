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
     * @property countryName the name of the country
     * @property exports the list of exports to be shown
     */
    data class ShowExportsChartAndCountryName(
        val countryName: String,
        val exports: Tree<UiExport>,
    ) : TradeFlowsAction()

    /**
     * @property exports the tree representing exports
     */
    data class ShowExportsChart(
        val exports: Tree<UiExport>,
    ) : TradeFlowsAction()

    /**
     * @property message the loading step message
     */
    data class LoadPartOfExports(val message: String) : TradeFlowsAction()
}