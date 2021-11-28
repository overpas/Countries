/**
 * DI container
 */

package by.overpass.countries.economies.di

import by.overpass.countries.data.NetworkComponent
import by.overpass.countries.data.RealNetworkComponent
import by.overpass.countries.feature.countries.CountriesComponent
import by.overpass.countries.feature.countries.RealCountriesComponent
import by.overpass.countries.feature.trade.flows.RealTradeFlowsComponent
import by.overpass.countries.feature.trade.flows.TradeFlowsComponent

interface AppComponent {

    fun countriesComponent(): CountriesComponent

    fun homeComponent(): HomeComponent

    fun tradeFlowsComponent(countryId: String): TradeFlowsComponent
}

object RealAppComponent : AppComponent {

    private val networkComponent: NetworkComponent = RealNetworkComponent

    override fun countriesComponent(): CountriesComponent = RealCountriesComponent(networkComponent)

    override fun homeComponent(): HomeComponent = RealHomeComponent

    override fun tradeFlowsComponent(countryId: String): TradeFlowsComponent =
        RealTradeFlowsComponent(networkComponent, countryId)
}