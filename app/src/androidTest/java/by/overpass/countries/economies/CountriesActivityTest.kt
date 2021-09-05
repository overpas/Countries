package by.overpass.countries.economies

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CountriesActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCountriesOpened() {
        composeTestRule.setContent {
            CountriesApp()
        }

        composeTestRule
            .onNodeWithContentDescription("Countries")
            .assertExists()
    }

    @Test
    fun testMovesToProducts() {
        composeTestRule.setContent {
            CountriesApp()
        }

        composeTestRule
            .onNodeWithText("Products")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Products")
            .assertExists()
    }

    @Test
    fun testMovesToSettings() {
        composeTestRule.setContent {
            CountriesApp()
        }

        composeTestRule
            .onNodeWithText("Settings")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Settings")
            .assertExists()
    }
}