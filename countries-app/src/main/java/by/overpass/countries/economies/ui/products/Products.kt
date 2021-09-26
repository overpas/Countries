/**
 * Products UI
 */

package by.overpass.countries.economies.ui.products

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.economies.ui.theme.CountriesTheme

@Suppress("UnusedPrivateMember")
@Composable
fun Products(navHostController: NavHostController) {
    Text("Products")
}

@Preview
@Composable
fun PreviewProducts() {
    CountriesTheme {
        Products(navHostController = rememberNavController())
    }
}