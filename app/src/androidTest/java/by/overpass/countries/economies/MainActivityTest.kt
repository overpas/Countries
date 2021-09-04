package by.overpass.countries.economies

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTextIsDisplayed() {
        composeTestRule.setContent {
            MainScreen(text = "Test")
        }

        composeTestRule
            .onNodeWithText("Test")
            .assertExists()
    }
}