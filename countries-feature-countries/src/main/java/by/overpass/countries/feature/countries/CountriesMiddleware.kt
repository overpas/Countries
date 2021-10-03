package by.overpass.countries.feature.countries

import by.overpass.countries.data.OecApi
import by.overpass.countries.redux.Middleware

class CountriesMiddleware(
    private val oecApi: OecApi
) : Middleware<CountriesState, CountriesAction> {

    override suspend fun processAction(
        state: CountriesState,
        action: CountriesAction
    ): CountriesAction = when (action) {
        is CountriesAction.LoadCountries ->
            CountriesAction.ShowCountries(
                oecApi.getCountries()
                    .countries
                    .filter { it.displayId != null }
            )
        is CountriesAction.ClickCountry -> TODO()
        is CountriesAction.ShowCountries -> action.copy()
    }
}