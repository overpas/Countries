package by.overpass.countries.feature.countries

import org.junit.Assert.assertTrue
import org.junit.Test

class CountriesReducerTest {

    private val countriesReducer = CountriesReducer()

    @Test
    fun `show countries - countries loaded`() {
        val resultState = countriesReducer.reduce(
            CountriesState.Loading,
            CountriesAction.ShowCountries(listOf()),
        )
        assertTrue(resultState is CountriesState.CountriesLoaded)
    }

    @Test
    fun `load countries - countries loading`() {
        val resultState = countriesReducer.reduce(
            CountriesState.Error("message"),
            CountriesAction.LoadCountries,
        )
        assertTrue(resultState is CountriesState.Loading)
    }

    @Test(expected = NotImplementedError::class)
    fun `click country - not implemented`() {
        val resultState = countriesReducer.reduce(
            CountriesState.CountriesLoaded(listOf()),
            CountriesAction.ClickCountry("id"),
        )
        assertTrue(resultState is CountriesState.Loading)
    }
}