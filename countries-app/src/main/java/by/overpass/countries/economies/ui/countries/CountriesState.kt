package by.overpass.countries.economies.ui.countries

import by.overpass.countries.redux.State

/**
 * Countries States
 */
sealed class CountriesState : State {

    object Loading : CountriesState()

    /**
     * @property countries the list of loaded countries to be displayed
     */
    data class CountriesLoaded(
        val countries: List<UiCountry>
    ) : CountriesState()

    /**
     * @property message the message of the error
     */
    data class Error(
        val message: String
    ) : CountriesState()
}
