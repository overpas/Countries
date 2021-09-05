/**
 * Countries UI
 */

package by.overpass.countries.economies.ui.countries

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.economies.ui.theme.CountriesTheme

@Composable
fun Countries(navHostController: NavHostController) {
    Text("Countries")
}

@Preview
@Composable
fun PreviewCountries() {
    CountriesTheme {
        Countries(navHostController = rememberNavController())
    }
}