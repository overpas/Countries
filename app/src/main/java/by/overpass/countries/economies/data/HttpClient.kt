/**
 * Http client
 */

package by.overpass.countries.economies.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

val httpClient: HttpClient = HttpClient(OkHttp) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
        })
    }
}