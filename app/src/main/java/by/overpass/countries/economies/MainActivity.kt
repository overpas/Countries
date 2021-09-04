package by.overpass.countries.economies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import by.overpass.countries.economies.ui.theme.CountriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(stringResource(R.string.app_name))
                }
            }
        }
    }
}

@Composable
fun MainScreen(text: String) {
    Text(text = text)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountriesTheme {
        MainScreen("Main Screen")
    }
}