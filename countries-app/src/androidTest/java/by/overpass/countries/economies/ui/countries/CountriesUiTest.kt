package by.overpass.countries.economies.ui.countries

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.economies.MockOecApi
import by.overpass.countries.economies.di.RealAppComponent
import by.overpass.countries.redux.android.store
import by.overpass.countries.redux.reducer
import org.junit.Rule
import org.junit.Test

class CountriesUiTest {

    private val countriesComponent = RealAppComponent.countriesComponent()
    private val mockApi = MockOecApi()
    private val middleware = countriesComponent.countriesMiddleware(mockApi)

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testLoading() {
        composeTestRule.setContent {
            Countries(
                rememberNavController(),
                store {
                    countriesComponent.countriesStore(
                        countriesMiddleware = middleware,
                        countriesReducer = reducer { state, action ->
                            CountriesState.Loading
                        },
                    )
                }
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()
    }

    @Test
    fun testCountriesLoaded() {
        composeTestRule.setContent {
            Countries(
                rememberNavController(),
                store {
                    countriesComponent.countriesStore(countriesMiddleware = middleware)
                }
            )
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
                rememberNavController(),
                store {
                    countriesComponent.countriesStore(
                        countriesMiddleware = middleware,
                        countriesReducer = reducer { state: CountriesState, action: CountriesAction ->
                            CountriesState.Error("message")
                        },
                    )
                }
            )
        }
    }
}