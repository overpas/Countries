package by.overpass.countries.economies.ui.countries

import by.overpass.countries.redux.State


sealed class CountriesState : State {

    object Loading : CountriesState()

    data class CountriesLoaded(
        val countries: List<UiCountry>
    ) : CountriesState()

    data class Error(
        val message: String
    ) : CountriesState()
}
