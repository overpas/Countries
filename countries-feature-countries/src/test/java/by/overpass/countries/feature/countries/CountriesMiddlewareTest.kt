@file:Suppress("FILE_UNORDERED_IMPORTS")

package by.overpass.countries.feature.countries

import by.overpass.countries.data.OecApi
import by.overpass.countries.data.countries.CountriesResponse
import by.overpass.countries.data.flows.Hs92ExportsImports
import by.overpass.countries.data.products.Products
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class CountriesMiddlewareTest {

    private val stubOecApi: OecApi = object : OecApi {
        override suspend fun getCountries(): CountriesResponse = CountriesResponse(listOf())

        override suspend fun getHs92TradeFlows(
            countryId: String,
            tradeFlow: String,
            year: Int,
            destination: String,
            products: String
        ): Hs92ExportsImports = TODO("Not yet implemented")

        override suspend fun getProducts(classification: String): Products =
            TODO("Not yet implemented")
    }
    private val countriesMiddleware = CountriesMiddleware(stubOecApi)

    @Test
    fun `load countries - show countries`() = runBlocking {
        val resultAction = countriesMiddleware.processAction(
            CountriesState.Loading,
            CountriesAction.LoadCountries,
        )
        assertTrue(resultAction.first() is CountriesAction.ShowCountries)
    }

    @Test
    fun `show countries - show countries`() = runBlocking {
        val resultAction = countriesMiddleware.processAction(
            CountriesState.Loading,
            CountriesAction.ShowCountries(listOf()),
        )
        assertTrue(resultAction.first() is CountriesAction.ShowCountries)
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