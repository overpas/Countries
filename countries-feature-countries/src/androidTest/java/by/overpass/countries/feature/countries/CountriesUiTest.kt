package by.overpass.countries.feature.countries

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import by.overpass.countries.data.MockOecApi
import by.overpass.countries.data.RealNetworkComponent
import by.overpass.countries.redux.android.store
import by.overpass.countries.redux.reducer
import org.junit.Rule
import org.junit.Test

class CountriesUiTest {

    private val countriesComponent = RealCountriesComponent(RealNetworkComponent)
    private val mockApi = MockOecApi()
    private val middleware = countriesComponent.countriesMiddleware(mockApi)

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testLoading() {
        composeTestRule.setContent {
            Countries(
                store {
                    countriesComponent.countriesStore(
                        countriesMiddleware = middleware,
                        countriesReducer = reducer { state, action ->
                            CountriesState.Loading
                        },
                    )
                },
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) { countryId, navController -> }
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()
    }

    @Test
    fun testCountriesLoaded() {
        composeTestRule.setContent {
            Countries(
                store {
                    countriesComponent.countriesStore(countriesMiddleware = middleware)
                },
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) { countryId, navController -> }
        }

        composeTestRule
            .onNodeWithText("Libya")
            .assertExists()

        composeTestRule
            .onNodeWithText("Lesotho")
            .assertExists()

        composeTestRule
            .onNodeWithText("Morocco")
            .assertExists()
    }

    @Test(expected = NotImplementedError::class)
    fun testCountriesLoadingError() {
        composeTestRule.setContent {
            Countries(
                store {
                    countriesComponent.countriesStore(
                        countriesMiddleware = middleware,
                        countriesReducer = reducer { state: CountriesState, action: CountriesAction ->
                            CountriesState.Error("message")
                        },
                    )
                },
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) { countryId, navController -> }
        }
    }
}