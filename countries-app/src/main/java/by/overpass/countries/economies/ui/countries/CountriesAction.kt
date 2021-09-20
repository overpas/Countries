package by.overpass.countries.economies.ui.countries

import by.overpass.countries.redux.Action

/**
 * Countries Actions
 */
sealed class CountriesAction : Action {

    object LoadCountries : CountriesAction()

    /**
     * @property countries the list of countries to be shown
     */
    data class ShowCountries(
        val countries: List<Country>
    ) : CountriesAction()

    /**
     * @property id the identifier of the clicked country
     */
    data class ClickCountry(
        val id: String
    ) : CountriesAction()
}
