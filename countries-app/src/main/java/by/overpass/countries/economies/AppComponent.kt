package by.overpass.countries.economies

import by.overpass.countries.economies.data.LegacyOecApi
import by.overpass.countries.economies.data.OEC_LEGACY_API_BASE_URL
import by.overpass.countries.economies.data.OecApi
import by.overpass.countries.economies.ui.countries.CountriesAction
import by.overpass.countries.economies.ui.countries.CountriesMiddleware
import by.overpass.countries.economies.ui.countries.CountriesReducer
import by.overpass.countries.economies.ui.countries.CountriesState
import by.overpass.countries.redux.Middleware
import by.overpass.countries.redux.Reducer
import by.overpass.countries.redux.Store
import io.ktor.client.HttpClient

interface AppComponent {

    fun httpClient(): HttpClient

    fun oecBaseUrl(): String

    fun countriesOecApi(
        httpClient: HttpClient = httpClient(),
        oecBaseUrl: String = oecBaseUrl(),
    ): OecApi

    fun countriesMiddleware(
        oecApi: OecApi = countriesOecApi(),
    ): Middleware<CountriesState, CountriesAction>

    fun countriesReducer(): Reducer<CountriesState, CountriesAction>

    fun countriesStore(
        countriesMiddleware: Middleware<CountriesState, CountriesAction> = countriesMiddleware(),
        countriesReducer: Reducer<CountriesState, CountriesAction> = countriesReducer()
    ): Store<CountriesState, CountriesAction>
}

object RealAppComponent : AppComponent {

    override fun httpClient(): HttpClient = by.overpass.countries.economies.data.httpClient

    override fun oecBaseUrl(): String = OEC_LEGACY_API_BASE_URL

    override fun countriesOecApi(
        httpClient: HttpClient,
        oecBaseUrl: String,
    ): OecApi = LegacyOecApi(
        httpClient,
        oecBaseUrl,
    )

    override fun countriesMiddleware(
        oecApi: OecApi
    ): Middleware<CountriesState, CountriesAction> = CountriesMiddleware(oecApi)

    override fun countriesReducer(): Reducer<CountriesState, CountriesAction> =
        CountriesReducer()

    override fun countriesStore(
        countriesMiddleware: Middleware<CountriesState, CountriesAction>,
        countriesReducer: Reducer<CountriesState, CountriesAction>,
    ): Store<CountriesState, CountriesAction> = SimpleAndroidViewModelStore(
        countriesMiddleware,
        countriesReducer,
        CountriesState.Loading,
    )
}