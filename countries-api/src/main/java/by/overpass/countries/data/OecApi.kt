/**
 * OEC API
 */

@file:Suppress(
    "LONG_NUMERICAL_VALUES_SEPARATED",
    "FILE_UNORDERED_IMPORTS",
    "CONSTANT_UPPERCASE",
)

package by.overpass.countries.data

import by.overpass.countries.data.countries.CountriesResponse
import by.overpass.countries.data.flows.Hs92ExportsImports
import by.overpass.countries.data.products.Products
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val HS92_ID_LENGTH_6 = 6.0

internal const val ALL = "all"
internal const val SHOW = "show"
internal const val LAST_YEAR = 2017

internal const val OEC_LEGACY_API_BASE_URL = "https://legacy.oec.world/"

interface OecApi {

    suspend fun getCountries(): CountriesResponse

    suspend fun getHs92TradeFlows(
        countryId: String,
        tradeFlow: String = TradeFlows.EXPORT.value,
        year: Int = LAST_YEAR,
        destination: String = ALL,
        products: String = SHOW,
    ): Hs92ExportsImports

    suspend fun getProducts(classification: String = Classifications.HS92.value): Products
}

internal class LegacyOecApi(
    private val client: HttpClient,
    private val baseUrl: String = OEC_LEGACY_API_BASE_URL,
) : OecApi {

    override suspend fun getCountries(): CountriesResponse =
        client.get("${baseUrl}attr/country/")

    override suspend fun getHs92TradeFlows(
        countryId: String,
        tradeFlow: String,
        year: Int,
        destination: String,
        products: String,
    ): Hs92ExportsImports =
        client.get(
            "$baseUrl${Classifications.HS92.value}/$tradeFlow/$year/$countryId" +
                    "/$destination/$products/"
        )

    override suspend fun getProducts(classification: String): Products =
        client.get("${baseUrl}attr/$classification/")
}