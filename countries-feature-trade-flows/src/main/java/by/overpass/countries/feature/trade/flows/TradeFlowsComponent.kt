/**
 * Trade Flows DI component
 */

package by.overpass.countries.feature.trade.flows

import by.overpass.countries.data.NetworkComponent
import by.overpass.countries.data.OecApi
import by.overpass.countries.redux.Middleware
import by.overpass.countries.redux.Reducer
import by.overpass.countries.redux.Store
import by.overpass.countries.redux.android.SimpleAndroidViewModelStore

interface TradeFlowsComponent {

    val networkComponent: NetworkComponent

    fun tradeFlowsReducer(): Reducer<TradeFlowsState, TradeFlowsAction>

    fun tradeFlowsMiddleware(
        oecApi: OecApi = networkComponent.countriesOecApi(),
    ): Middleware<TradeFlowsState, TradeFlowsAction>

    fun tradeFlowsStore(
        middleware: Middleware<TradeFlowsState, TradeFlowsAction> = tradeFlowsMiddleware(),
        reducer: Reducer<TradeFlowsState, TradeFlowsAction> = tradeFlowsReducer(),
    ): Store<TradeFlowsState, TradeFlowsAction>
}

class RealTradeFlowsComponent(
    override val networkComponent: NetworkComponent,
    private val countryId: String,
) : TradeFlowsComponent {

    override fun tradeFlowsReducer(): Reducer<TradeFlowsState, TradeFlowsAction> =
        TradeFlowsReducer()

    override fun tradeFlowsMiddleware(oecApi: OecApi): Middleware<TradeFlowsState, TradeFlowsAction> =
        TradeFlowsMiddleware(oecApi, countryId)

    override fun tradeFlowsStore(
        middleware: Middleware<TradeFlowsState, TradeFlowsAction>,
        reducer: Reducer<TradeFlowsState, TradeFlowsAction>
    ): Store<TradeFlowsState, TradeFlowsAction> =
        SimpleAndroidViewModelStore(middleware, reducer, TradeFlowsState.Loading)
}