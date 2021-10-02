/**
 * Home State
 */

package by.overpass.countries.economies.ui.home

import by.overpass.countries.redux.State

/**
 * @property bottomNavItems the navigation items to be displayed at the bottom
 * @property destinations the possible destinations from home
 */
data class HomeState(
    val bottomNavItems: List<UiBottomNavItem>,
    val destinations: HomeDestinations,
) : State {

    companion object {
        fun initial(): HomeState = HomeState(
            listOf(
                UiBottomNavItem.Countries,
                UiBottomNavItem.Products,
                UiBottomNavItem.Settings,
            ),
            HomeDestinations(
                UiBottomNavItem.Countries.route,
                UiBottomNavItem.Products.route,
                UiBottomNavItem.Settings.route,
            ),
        )
    }
}

/**
 * @property countries destination of Countries
 * @property products destination of Products
 * @property settings destination of Settings
 * @property start the initial destination
 */
data class HomeDestinations(
    val countries: String,
    val products: String,
    val settings: String,
    val start: String = countries,
)