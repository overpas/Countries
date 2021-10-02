/**
 * App entry
 */

package by.overpass.countries.economies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.economies.di.AppComponent
import by.overpass.countries.economies.di.RealAppComponent
import by.overpass.countries.economies.ui.home.Home
import by.overpass.countries.economies.ui.theme.CountriesTheme
import by.overpass.countries.redux.android.store

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesApp(RealAppComponent)
        }
    }
}

@Composable
fun CountriesApp(appComponent: AppComponent) {
    CountriesTheme {
        val navController = rememberNavController()
        Home(
            appComponent,
            navController,
            store {
                appComponent.homeComponent().homeStore()
            }
        )
    }
}
