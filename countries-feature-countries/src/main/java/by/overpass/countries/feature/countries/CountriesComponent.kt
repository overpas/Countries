/**
 * Countries DI component
 */

package by.overpass.countries.feature.countries

import by.overpass.countries.data.NetworkComponent
import by.overpass.countries.data.OecApi
import by.overpass.countries.redux.Middleware
import by.overpass.countries.redux.Reducer
import by.overpass.countries.redux.Store
import by.overpass.countries.redux.android.SimpleAndroidViewModelStore

interface CountriesComponent {

    val networkComponent: NetworkComponent

    fun countriesMiddleware(
        oecApi: OecApi = networkComponent.countriesOecApi(),
    ): Middleware<CountriesState, CountriesAction>

    fun countriesReducer(): Reducer<CountriesState, CountriesAction>

    fun countriesStore(
        countriesMiddleware: Middleware<CountriesState, CountriesAction> = countriesMiddleware(),
        countriesReducer: Reducer<CountriesState, CountriesAction> = countriesReducer(),
    ): Store<CountriesState, CountriesAction>
}

class RealCountriesComponent(
    override val networkComponent: NetworkComponent
) : CountriesComponent {

    override fun countriesMiddleware(oecApi: OecApi): Middleware<CountriesState, CountriesAction> =
        CountriesMiddleware(oecApi)

    override fun countriesReducer(): Reducer<CountriesState, CountriesAction> = CountriesReducer()

    override fun countriesStore(
        countriesMiddleware: Middleware<CountriesState, CountriesAction>,
        countriesReducer: Reducer<CountriesState, CountriesAction>
    ): Store<CountriesState, CountriesAction> = SimpleAndroidViewModelStore(
        countriesMiddleware,
        countriesReducer,
        CountriesState.Loading,
    )
}