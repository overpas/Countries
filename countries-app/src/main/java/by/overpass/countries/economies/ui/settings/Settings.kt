/**
 * Settings UI
 */

package by.overpass.countries.economies.ui.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.ui.common.theme.CountriesTheme

@Suppress("UnusedPrivateMember")
@Composable
fun Settings(navHostController: NavHostController) {
    Text("Settings")
}

@Preview
@Composable
fun PreviewSettings() {
    CountriesTheme {
        Settings(navHostController = rememberNavController())
    }
}