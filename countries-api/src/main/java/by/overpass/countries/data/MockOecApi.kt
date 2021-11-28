@file:Suppress("LONG_NUMERICAL_VALUES_SEPARATED")

package by.overpass.countries.data

import by.overpass.countries.data.countries.CountriesResponse
import by.overpass.countries.data.countries.Country
import by.overpass.countries.data.flows.Hs92ExportsImports
import by.overpass.countries.data.flows.Hs92ProductExportsImports
import by.overpass.countries.data.products.Product
import by.overpass.countries.data.products.Products

class MockOecApi : OecApi {

    override suspend fun getCountries(): CountriesResponse = CountriesResponse(testCountries)

    override suspend fun getHs92TradeFlows(
        countryId: String,
        tradeFlow: String,
        year: Int,
        destination: String,
        products: String
    ): Hs92ExportsImports = Hs92ExportsImports(testExportsImports)

    override suspend fun getProducts(classification: String): Products = Products(testProducts)

    companion object {
        val testCountries = listOf(
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
                weight = 8_998_983_149.32,
            ),
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
                weight = 27_464_888_346.94,
            ),
        )
        val testExportsImports = listOf(
            Hs92ProductExportsImports(
                exportRca = 2.284,
                exportVal = 201413947.08,
                exportValGrowthPct = -0.071,
                exportValGrowthPct5 = 0.001,
                exportValGrowthVal = -15485573.07,
                exportValGrowthVal5 = 1013855.32,
                hs92Id = "01010111",
                hs92IdLen = 8.0,
                importRca = 0.181,
                importVal = 24912193.67,
                importValGrowthPct = -0.387,
                importValGrowthPct5 = -0.107,
                importValGrowthVal = -15704026.59,
                importValGrowthVal5 = -18950600.04,
                originId = "nausa",
                year = 2010.0,
            ),
            Hs92ProductExportsImports(
                exportRca = 2.393,
                exportVal = 189291730.77,
                exportValGrowthPct = 0.039,
                exportValGrowthPct5 = 0.108,
                exportValGrowthVal = 7049210.7,
                exportValGrowthVal5 = 75792468.76,
                hs92Id = "01010119",
                hs92IdLen = 8.0,
                importRca = 1.819,
                importVal = 225214253.39,
                importValGrowthPct = 0.111,
                importValGrowthPct5 = -0.038,
                importValGrowthVal = 22562795.93,
                importValGrowthVal5 = -48093340.35,
                originId = "nausa",
                year = 2010.0,
            ),
        )
        val testProducts = listOf(
            Product(
                color = "#FFE999",
                displayId = "0101",
                icon = "/static/img/icons/hs/hs_01.png",
                id = "010101",
                image = "/static/img/headers/hs/010101.jpg",
                imageAuthor = "James Marvin Phelps",
                imageLink = "https://flic.kr/p/gMG1YC",
                keywords = "equine, zebra, ass, donkey, mules",
                name = "Horses",
                palette = "[\"#e7f9fa\",\"#533127\",\"#907b65\",\"#713928\",\"#b66b48\"]",
                weight = 2602431195.48,
            ),
            Product(
                color = "#3ab11a",
                displayId = "660320",
                icon = "/static/img/icons/hs/hs_12.png",
                id = "12660320",
                image = "/static/img/headers/hs/12.jpg",
                keywords = null,
                name = "Umbrella frames",
                weight = 104352664.94,
            ),
        )
    }
}