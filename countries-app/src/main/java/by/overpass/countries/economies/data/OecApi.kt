/**
 * OEC API
 */

package by.overpass.countries.economies.data

import by.overpass.countries.economies.ui.countries.CountriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val OEC_LEGACY_API_BASE_URL = "https://legacy.oec.world/"

interface OecApi {

    suspend fun getCountries(): CountriesResponse
}

class LegacyOecApi(
    private val client: HttpClient,
    private val baseUrl: String = OEC_LEGACY_API_BASE_URL
) : OecApi {

    override suspend fun getCountries(): CountriesResponse = client.get("${baseUrl}attr/country/")
}