/**
 * Countries DI component
 */

package by.overpass.countries.economies.di

import by.overpass.countries.economies.data.OecApi
import by.overpass.countries.economies.ui.countries.CountriesAction
import by.overpass.countries.economies.ui.countries.CountriesMiddleware
import by.overpass.countries.economies.ui.countries.CountriesReducer
import by.overpass.countries.economies.ui.countries.CountriesState
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