package by.overpass.countries.feature.countries

import by.overpass.countries.redux.Reducer

class CountriesReducer : Reducer<CountriesState, CountriesAction> {

    override fun reduce(state: CountriesState, action: CountriesAction): CountriesState =
        when (action) {
            is CountriesAction.ShowCountries -> CountriesState.CountriesLoaded(
                action.countries.map {
                    UiCountry(
                        it.name,
                        it.imageUrl,
                        it.displayId!!
                    )
                }
            )
            is CountriesAction.LoadCountries -> CountriesState.Loading
            is CountriesAction.ClickCountry -> TODO()
        }
}