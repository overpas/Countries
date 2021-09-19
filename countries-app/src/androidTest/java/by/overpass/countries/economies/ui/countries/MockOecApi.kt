package by.overpass.countries.economies.ui.countries

import by.overpass.countries.economies.data.OecApi

class MockOecApi : OecApi {

    override suspend fun getCountries(): CountriesResponse = CountriesResponse(testCountries)

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
                weight = 8998983149.32,
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
                weight = 27464888346.94,
            ),
        )
    }
}