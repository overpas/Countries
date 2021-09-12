/**
 * Countries UI
 */

package by.overpass.countries.economies.ui.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberImagePainter

@Composable
fun Countries(
    navHostController: NavHostController,
    countriesViewModel: CountriesViewModel,
) {
    val countries: List<Country> by countriesViewModel.countries.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(countries) { country ->
            CountryItem(country = country)
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    val itemHeight = 80.dp
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(Shapes.medium),
    ) {
        Image(
            painter = rememberImagePainter(country.imageUrl),
            contentDescription = country.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
        )
        GradientBgText(
            text = country.name,
            gradientBrush = Brush.verticalGradient(
                0F to Color(0x00_FF_FF_FF),
                0.8F to Color(0x50_00_00_00),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
        )
    }
}

@Composable
fun GradientBgText(
    text: String,
    gradientBrush: Brush,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(gradientBrush),
        contentAlignment = Alignment.BottomCenter,
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

@Preview
@Composable
fun PreviewCountryItem() {
    CountriesTheme {
        CountryItem(
            Country(
                "China",
                null,
                null,
                "color",
                null,
                null,
                null,
                null,
                null,
                null,
                "https://legacy.oec.world/static/img/headers/country/eu.jpg",
                null,
                null,
                null,
                null,
            )
        )
    }
}