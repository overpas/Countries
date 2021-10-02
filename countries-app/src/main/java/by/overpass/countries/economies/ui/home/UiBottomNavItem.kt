package by.overpass.countries.economies.ui.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import by.overpass.countries.economies.R

/**
 * @property route the item identifier for Jetpack Navigation
 * @property resourceId the string resource of the item
 * @property icon the icon of the item
 */
sealed class UiBottomNavItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {

    object Countries : UiBottomNavItem(
        "countries",
        R.string.title_countries,
        Icons.Filled.Place,
    )

    object Products : UiBottomNavItem(
        "products",
        R.string.title_products,
        Icons.Filled.ShoppingCart,
    )

    object Settings : UiBottomNavItem(
        "settings",
        R.string.title_settings,
        Icons.Filled.Settings,
    )

    companion object {
        fun all(): List<UiBottomNavItem> = listOf(
            Countries,
            Products,
            Settings,
        )
    }
}