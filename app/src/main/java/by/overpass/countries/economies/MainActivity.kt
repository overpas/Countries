/**
 * App entry
 */

package by.overpass.countries.economies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.economies.data.LegacyOecApi
import by.overpass.countries.economies.data.httpClient
import by.overpass.countries.economies.ui.countries.Countries
import by.overpass.countries.economies.ui.countries.CountriesViewModel
import by.overpass.countries.economies.ui.products.Products
import by.overpass.countries.economies.ui.settings.Settings
import by.overpass.countries.economies.ui.theme.CountriesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesApp()
        }
    }
}

/**
 * @property route the item identifier for Jetpack Navigation
 * @property resourceId the string resource of the item
 * @property icon the icon of the item
 */
sealed class BottomNavItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {

    object Countries : BottomNavItem(
        "countries",
        R.string.title_countries,
        Icons.Filled.Place,
    )

    object Products : BottomNavItem(
        "products",
        R.string.title_products,
        Icons.Filled.ShoppingCart,
    )

    object Settings : BottomNavItem(
        "settings",
        R.string.title_settings,
        Icons.Filled.Settings,
    )

    companion object {
        fun all(): List<BottomNavItem> = listOf(
            Countries,
            Products,
            Settings,
        )
    }
}

@Composable
fun RowScope.CountriesAppBottomNavItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        icon = { Icon(item.icon, stringResource(item.resourceId)) },
        label = { Text(stringResource(item.resourceId)) },
        selected = currentDestination
            ?.hierarchy
            ?.any { it.route == item.route }
            ?: false,
        onClick = onClick,
    )
}

@Composable
fun CountriesApp() {
    CountriesTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    BottomNavItem.all().forEach { item ->
                        CountriesAppBottomNavItem(
                            item = item,
                            currentDestination = currentDestination
                        ) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            CountriesNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CountriesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Countries.route,
        modifier,
    ) {
        composable(BottomNavItem.Countries.route) {
            Countries(
                navController,
                viewModel(factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                        CountriesViewModel(LegacyOecApi(httpClient)) as T
                }),
            )
        }
        composable(BottomNavItem.Products.route) { Products(navController) }
        composable(BottomNavItem.Settings.route) { Settings(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountriesTheme {
        CountriesApp()
    }
}