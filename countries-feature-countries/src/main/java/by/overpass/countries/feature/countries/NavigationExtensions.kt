/**
 * Extensions related to navigation
 */

package by.overpass.countries.feature.countries

import androidx.navigation.NavBackStackEntry

fun <T> NavBackStackEntry.arg(name: String): T =
    arguments!!.get(name) as T