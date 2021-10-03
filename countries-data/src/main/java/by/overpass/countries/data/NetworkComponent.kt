/**
 * Network Component
 */

package by.overpass.countries.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

interface NetworkComponent {

    fun clientEngineFactory(): HttpClientEngineFactory<*>

    fun serializer(): KotlinxSerializer

    fun httpClient(
        engineFactory: HttpClientEngineFactory<*> = clientEngineFactory(),
        serializer: KotlinxSerializer = serializer(),
    ): HttpClient

    fun oecBaseUrl(): String

    fun countriesOecApi(
        httpClient: HttpClient = httpClient(),
        oecBaseUrl: String = oecBaseUrl(),
    ): OecApi
}

object RealNetworkComponent : NetworkComponent {

    override fun clientEngineFactory(): HttpClientEngineFactory<*> = OkHttp

    override fun serializer(): KotlinxSerializer = KotlinxSerializer(
        Json {
            prettyPrint = true
        }
    )

    override fun httpClient(
        engineFactory: HttpClientEngineFactory<*>,
        serializer: KotlinxSerializer,
    ): HttpClient = HttpClient(engineFactory) {
        install(JsonFeature) {
            this.serializer = serializer
        }
    }

    override fun oecBaseUrl(): String = OEC_LEGACY_API_BASE_URL

    override fun countriesOecApi(httpClient: HttpClient, oecBaseUrl: String): OecApi = LegacyOecApi(
        httpClient,
        oecBaseUrl,
    )
}