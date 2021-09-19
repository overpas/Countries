package by.overpass.countries.economies.ui.countries

import by.overpass.countries.economies.data.OecApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test


class CountriesMiddlewareTest {

    private val stubOecApi: OecApi = object : OecApi {
        override suspend fun getCountries(): CountriesResponse = CountriesResponse(listOf())
    }
    private val countriesMiddleware = CountriesMiddleware(stubOecApi)

    @Test
    fun `load countries - show countries`() = runBlocking {
        val resultAction = countriesMiddleware.processAction(
            CountriesState.Loading,
            CountriesAction.LoadCountries,
        )
        assertTrue(resultAction is CountriesAction.ShowCountries)
    }

    @Test
    fun `show countries - show countries`() = runBlocking {
        val resultAction = countriesMiddleware.processAction(
            CountriesState.Loading,
            CountriesAction.ShowCountries(listOf()),
        )
        assertTrue(resultAction is CountriesAction.ShowCountries)
    }

    @Test(expected = NotImplementedError::class)
    fun `click country - not implemented`() = runBlocking {
        countriesMiddleware.processAction(
            CountriesState.Loading,
            CountriesAction.ClickCountry("id"),
        )
        Unit
    }
}