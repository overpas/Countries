package by.overpass.countries.economies.data

import by.overpass.countries.economies.ui.countries.Country
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LegacyOecApiTest {

    private val mockEngine = MockEngine { request ->
        respond(
            content = """
                        {
                          "data": [
                            {
                              "borders_land": "['afdza', 'aftcd', 'afegy', 'afner', 'afsdn', 'aftun']",
                              "borders_maritime": "['eugrc', 'euita', 'eumlt']",
                              "color": "#ffc41c",
                              "comtrade_name": "Libya",
                              "display_id": "lby",
                              "icon": "/static/img/icons/country/country_aflby.png",
                              "id": "aflby",
                              "id_2char": "ly",
                              "id_num": "434",
                              "image": "/static/img/headers/country/aflby.jpg",
                              "image_author": "sejanc",
                              "image_link": "https://flic.kr/p/6fsy7W",
                              "name": "Libya",
                              "palette": "[\"#d49a64\",\"#1a0a06\",\"#5a3415\"]",
                              "weight": 8998983149.32
                            },
                            {
                              "borders_land": "['afzaf']",
                              "color": "#ffc41c",
                              "comtrade_name": "Lesotho",
                              "display_id": "lso",
                              "icon": "/static/img/icons/country/country_aflso.png",
                              "id": "aflso",
                              "id_2char": "ls",
                              "id_num": "426",
                              "image": "/static/img/headers/country/af.jpg",
                              "name": "Lesotho"
                            },
                            {
                              "borders_land": "['afdza', 'euesp', 'afmrt', 'afesh']",
                              "borders_maritime": "['euprt']",
                              "color": "#ffc41c",
                              "comtrade_name": "Morocco",
                              "display_id": "mar",
                              "icon": "/static/img/icons/country/country_afmar.png",
                              "id": "afmar",
                              "id_2char": "ma",
                              "id_num": "504",
                              "image": "/static/img/headers/country/afmar.jpg",
                              "image_author": "Jamie McCaffrey",
                              "image_link": "https://flic.kr/p/sNGpTR",
                              "name": "Morocco",
                              "palette": "[\"#5368bf\",\"#f8e4f1\"]",
                              "weight": 27464888346.94
                            }
                          ]
                        }
                    """.trimIndent(),
            status = HttpStatusCode.OK,
            headers = headersOf("Content-Type", "application/json")
        )
    }
    private val testHttpClient = HttpClient(mockEngine) {
        install(JsonFeature) {
            serializer = jsonSerializer
        }
    }
    private val oecApi = LegacyOecApi(testHttpClient)

    @Test
    fun `countries fetched`() = runBlocking {
        val countriesResponse = oecApi.getCountries()

        assertEquals(3, countriesResponse.countries.size)
        assertEquals(
            Country(
                name = "Libya",
                bordersLand = "['afdza', 'aftcd', 'afegy', 'afner', 'afsdn', 'aftun']",
                bordersMaritime = "['eugrc', 'euita', 'eumlt']",
                color = "#ffc41c",
                comtradeName = "Libya",
                displayId = "lby",
                icon = "/static/img/icons/country/country_aflby.png",
                id = "aflby",
                id2Char = "ly",
                idNum = "434",
                image = "/static/img/headers/country/aflby.jpg",
                imageAuthor = "sejanc",
                imageLink = "https://flic.kr/p/6fsy7W",
                palette = "[\"#d49a64\",\"#1a0a06\",\"#5a3415\"]",
                weight = 8998983149.32,
            ),
            countriesResponse.countries[0],
        )
        assertEquals(
            Country(
                name = "Lesotho",
                bordersLand = "['afzaf']",
                color = "#ffc41c",
                comtradeName = "Lesotho",
                displayId = "lso",
                icon = "/static/img/icons/country/country_aflso.png",
                id = "aflso",
                id2Char = "ls",
                idNum = "426",
                image = "/static/img/headers/country/af.jpg",
            ),
            countriesResponse.countries[1],
        )
        assertEquals(
            Country(
                name = "Morocco",
                bordersLand = "['afdza', 'euesp', 'afmrt', 'afesh']",
                bordersMaritime = "['euprt']",
                color = "#ffc41c",
                comtradeName = "Morocco",
                displayId = "mar",
                icon = "/static/img/icons/country/country_afmar.png",
                id = "afmar",
                id2Char = "ma",
                idNum = "504",
                image = "/static/img/headers/country/afmar.jpg",
                imageAuthor = "Jamie McCaffrey",
                imageLink = "https://flic.kr/p/sNGpTR",
                palette = "[\"#5368bf\",\"#f8e4f1\"]",
                weight = 27464888346.94,
            ),
            countriesResponse.countries[2],
        )
    }
}