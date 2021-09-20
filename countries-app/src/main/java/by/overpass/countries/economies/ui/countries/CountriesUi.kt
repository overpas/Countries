/**
 * Countries UI
 */

package by.overpass.countries.economies.ui.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.overpass.countries.economies.ui.theme.CountriesTheme
import by.overpass.countries.economies.ui.theme.Shapes
import by.overpass.countries.economies.ui.theme.Typography
import by.overpass.countries.redux.Store
import coil.compose.rememberImagePainter

@Composable
fun Countries(
    navHostController: NavHostController,
    countriesStore: Store<CountriesState, CountriesAction>,
) {
    val countriesState: CountriesState by countriesStore.state.collectAsState()
    LaunchedEffect(true) {
        countriesStore.dispatch(CountriesAction.LoadCountries)
    }
    CountriesList(navHostController, countriesState)
}

@Composable
fun CountriesList(
    navHostController: NavHostController,
    countriesState: CountriesState,
) {
    when (countriesState) {
        is CountriesState.CountriesLoaded -> CountriesLoaded(navHostController, countriesState)
        is CountriesState.Error -> TODO()
        is CountriesState.Loading -> CountriesLoading()
    }
}

@Composable
fun CountriesLoading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        CircularProgressIndicator(modifier)
    }
}

@Composable
fun CountriesLoaded(
    navHostController: NavHostController,
    countriesState: CountriesState.CountriesLoaded,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(countriesState.countries) { country ->
            CountryItem(country = country)
        }
    }
}

@Composable
fun CountryItem(country: UiCountry) {
    val itemHeight = 80.dp
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(Shapes.medium),
    ) {
        Image(
            painter = rememberImagePainter(country.url),
            contentDescription = country.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
        )
        TextWithDimmedBg(
            text = country.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
        )
    }
}

@Composable
fun TextWithDimmedBg(text: String, modifier: Modifier = Modifier) {
    GradientBg(
        gradientBrush = Brush.verticalGradient(
            0F to Color(0x00_FF_FF_FF),
            0.8F to Color(0x50_00_00_00),
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = Typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, bottom = 12.dp),
        )
    }
}

@Composable
fun GradientBg(
    gradientBrush: Brush,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(gradientBrush),
        contentAlignment = Alignment.BottomCenter,
    ) {
        text()
    }
}

@Preview
@Composable
fun PreviewGradientBg() {
    CountriesTheme {
        GradientBg(
            gradientBrush = Brush.verticalGradient(
                0F to Color(0x00_FF_FF_FF),
                0.8F to Color(0x50_00_00_00),
            ),
        ) {
            Text(text = "text")
        }
    }
}

@Preview
@Composable
fun PreviewCountryItem() {
    CountriesTheme {
        CountryItem(
            UiCountry(
                "China",
                "https://legacy.oec.world/static/img/headers/country/eu.jpg",
                "id",
            )
        )
    }
}

@Preview
@Composable
fun PreviewCountriesLoading() {
    CountriesTheme {
        CountriesLoading()
    }
}