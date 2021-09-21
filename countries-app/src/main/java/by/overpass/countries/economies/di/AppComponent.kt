/**
 * DI container
 */

package by.overpass.countries.economies.di

interface AppComponent {

    fun countriesComponent(): CountriesComponent
}

object RealAppComponent : AppComponent {

    private val networkComponent: NetworkComponent = RealNetworkComponent

    override fun countriesComponent(): CountriesComponent = RealCountriesComponent(networkComponent)
}