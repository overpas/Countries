/**
 * Home UI
 */

package by.overpass.countries.economies.ui.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import by.overpass.countries.economies.di.AppComponent
import by.overpass.countries.economies.ui.products.Products
import by.overpass.countries.economies.ui.settings.Settings
import by.overpass.countries.feature.countries.Countries
import by.overpass.countries.feature.countries.CountriesComponent
import by.overpass.countries.redux.Store
import by.overpass.countries.redux.android.store

@Composable
fun RowScope.CountriesAppBottomNavItem(
    item: UiBottomNavItem,
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
fun Home(
    appComponent: AppComponent,
    navController: NavHostController,
    homeStore: Store<HomeState, HomeAction>,
) {
    val homeState by homeStore.state.collectAsState()
    Scaffold(
        bottomBar = {
            HomeBottomNavItems(navController, homeState.bottomNavItems)
        }
    ) { innerPadding ->
        HomeNav(
            navController,
            appComponent,
            homeState.destinations,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun HomeBottomNavItems(navController: NavHostController, bottomNavItems: List<UiBottomNavItem>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavItems.forEach { item ->
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

@Composable
fun HomeNav(
    navController: NavHostController,
    appComponent: AppComponent,
    homeDestinations: HomeDestinations,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = homeDestinations.start,
        modifier,
    ) {
        composable(homeDestinations.countries) {
            CountriesDestination(navController, appComponent.countriesComponent())
        }
        composable(homeDestinations.products) {
            Products(navController)
        }
        composable(homeDestinations.settings) {
            Settings(navController)
        }
    }
}

@Composable
fun CountriesDestination(navController: NavHostController, countriesComponent: CountriesComponent) {
    Countries(
        navController,
        store {
            countriesComponent.countriesStore()
        },
    )
}