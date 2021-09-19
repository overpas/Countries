package by.overpass.countries.economies.ui.countries

import by.overpass.countries.redux.Action


sealed class CountriesAction : Action {

    object LoadCountries : CountriesAction()

    data class ShowCountries(
        val countries: List<Country>
    ) : CountriesAction()

    data class ClickCountry(
        val id: String
    ) : CountriesAction()
}
