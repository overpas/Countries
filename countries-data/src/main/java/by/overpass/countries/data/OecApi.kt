/**
 * OEC API
 */

package by.overpass.countries.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal const val OEC_LEGACY_API_BASE_URL = "https://legacy.oec.world/"

interface OecApi {

    suspend fun getCountries(): CountriesResponse
}

internal class LegacyOecApi(
    private val client: HttpClient,
    private val baseUrl: String = OEC_LEGACY_API_BASE_URL
) : OecApi {

    override suspend fun getCountries(): CountriesResponse = client.get("${baseUrl}attr/country/")
}