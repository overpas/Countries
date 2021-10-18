@file:Suppress("FILE_UNORDERED_IMPORTS")

import by.overpass.countries.data.LegacyOecApi
import by.overpass.countries.data.MockOecApi
import by.overpass.countries.data.RealNetworkComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LegacyOecApiTest {

    private val mockEngine = MockEngine { request ->
        val stringUrl = request.url.toString()
        when {
            stringUrl.endsWith("attr/country/") ->
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
            stringUrl.endsWith("attr/hs92/") ->
                respond(
                    content = """
                            {
                              "data": [
                                {
                                  "color": "#FFE999",
                                  "display_id": "0101",
                                  "icon": "/static/img/icons/hs/hs_01.png",
                                  "id": "010101",
                                  "image": "/static/img/headers/hs/010101.jpg",
                                  "image_author": "James Marvin Phelps",
                                  "image_link": "https://flic.kr/p/gMG1YC",
                                  "keywords": "equine, zebra, ass, donkey, mules",
                                  "name": "Horses",
                                  "palette": "[\"#e7f9fa\",\"#533127\",\"#907b65\",\"#713928\",\"#b66b48\"]",
                                  "weight": 2602431195.48
                                },
                                {
                                  "color": "#3ab11a",
                                  "display_id": "660320",
                                  "icon": "/static/img/icons/hs/hs_12.png",
                                  "id": "12660320",
                                  "image": "/static/img/headers/hs/12.jpg",
                                  "keywords": null,
                                  "name": "Umbrella frames",
                                  "weight": 104352664.94
                                }
                              ]
                            }
                        """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf("Content-Type", "application/json")
                )
            stringUrl.endsWith("hs92/export/2010/usa/all/show/") ->
                respond(
                    content = """
                            {
                              "data": [
                                {
                                  "export_rca": 2.284,
                                  "export_val": 201413947.08,
                                  "export_val_growth_pct": -0.071,
                                  "export_val_growth_pct_5": 0.001,
                                  "export_val_growth_val": -15485573.07,
                                  "export_val_growth_val_5": 1013855.32,
                                  "hs92_id": "01010111",
                                  "hs92_id_len": 8,
                                  "import_rca": 0.181,
                                  "import_val": 24912193.67,
                                  "import_val_growth_pct": -0.387,
                                  "import_val_growth_pct_5": -0.107,
                                  "import_val_growth_val": -15704026.59,
                                  "import_val_growth_val_5": -18950600.04,
                                  "origin_id": "nausa",
                                  "year": 2010
                                },
                                {
                                  "export_rca": 2.393,
                                  "export_val": 189291730.77,
                                  "export_val_growth_pct": 0.039,
                                  "export_val_growth_pct_5": 0.108,
                                  "export_val_growth_val": 7049210.7,
                                  "export_val_growth_val_5": 75792468.76,
                                  "hs92_id": "01010119",
                                  "hs92_id_len": 8,
                                  "import_rca": 1.819,
                                  "import_val": 225214253.39,
                                  "import_val_growth_pct": 0.111,
                                  "import_val_growth_pct_5": -0.038,
                                  "import_val_growth_val": 22562795.93,
                                  "import_val_growth_val_5": -48093340.35,
                                  "origin_id": "nausa",
                                  "year": 2010
                                }
                              ]
                            }
                        """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf("Content-Type", "application/json")
                )
            else -> respondError(HttpStatusCode.BadRequest)
        }
    }
    private val testHttpClient = HttpClient(mockEngine) {
        install(JsonFeature) {
            serializer = RealNetworkComponent.serializer()
        }
    }
    private val oecApi = LegacyOecApi(testHttpClient)

    @Test
    fun `countries fetched`() = runBlocking {
        val countriesResponse = oecApi.getCountries()

        assertEquals(3, countriesResponse.countries.size)
        assertEquals(MockOecApi.testCountries[0], countriesResponse.countries[0])
        assertEquals(MockOecApi.testCountries[1], countriesResponse.countries[1])
        assertEquals(MockOecApi.testCountries[2], countriesResponse.countries[2])
    }

    @Test
    fun `hs92 products fetched`() = runBlocking {
        val products = oecApi.getProducts()

        assertEquals(2, products.data.size)
        assertEquals(MockOecApi.testProducts[0], products.data[0])
        assertEquals(MockOecApi.testProducts[1], products.data[1])
    }

    @Test
    @Suppress("LONG_NUMERICAL_VALUES_SEPARATED")
    fun `hs92 trade flows fetched`() = runBlocking {
        val tradeFlows = oecApi.getHs92TradeFlows(countryId = "usa", year = 2010)

        assertEquals(2, tradeFlows.data.size)
        assertEquals(MockOecApi.testExportsImports[0], tradeFlows.data[0])
        assertEquals(MockOecApi.testExportsImports[1], tradeFlows.data[1])
    }
}