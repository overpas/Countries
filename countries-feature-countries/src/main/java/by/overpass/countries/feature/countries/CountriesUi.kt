/**
 * Countries UI
 */

@file:Suppress("FunctionParameterNaming")

package by.overpass.countries.feature.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import by.overpass.countries.redux.Store
import by.overpass.countries.ui.common.core.ContentLoading
import by.overpass.countries.ui.common.theme.CountriesTheme
import by.overpass.countries.ui.common.theme.DimmedBlack
import by.overpass.countries.ui.common.theme.Shapes
import by.overpass.countries.ui.common.theme.Transparent
import by.overpass.countries.ui.common.theme.Typography
import coil.compose.rememberImagePainter

@Composable
fun Countries(
    countriesStore: Store<CountriesState, CountriesAction>,
    modifier: Modifier = Modifier,
    TradeFlowsDestination: @Composable (
        countryId: String,
        navController: NavHostController
    ) -> Unit,
) {
    val countriesState: CountriesState by countriesStore.state.collectAsState()
    LaunchedEffect(true) {
        countriesStore.dispatch(CountriesAction.LoadCountries)
    }
    CountriesContent(countriesState, modifier, TradeFlowsDestination)
}

@Composable
fun CountriesContent(
    countriesState: CountriesState,
    modifier: Modifier = Modifier,
    TradeFlowsDestination: @Composable (
        countryId: String,
        navController: NavHostController
    ) -> Unit,
) {
    when (countriesState) {
        is CountriesState.CountriesLoaded -> CountriesLoaded(
            countriesState,
            modifier,
            TradeFlowsDestination
        )
        is CountriesState.Error -> TODO()
        is CountriesState.Loading -> ContentLoading(modifier)
    }
}

@Composable
fun CountriesLoaded(
    state: CountriesState.CountriesLoaded,
    modifier: Modifier = Modifier,
    TradeFlowsDestination: @Composable (
        countryId: String,
        navController: NavHostController
    ) -> Unit,
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = state.initialDestination,
        modifier = modifier
    ) {
        composable(state.countriesLoadedDestination) {
            CountriesLoadedContent(
                countriesState = state,
                navHostController = navHostController,
                modifier = Modifier.fillMaxWidth()
            )
        }
        composable(
            route = state.countryExportsDestination,
            arguments = listOf(
                navArgument(state.paramCountryId) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            TradeFlowsDestination(
                backStackEntry.arg(state.paramCountryId),
                navHostController
            )
        }
    }
}

@Composable
fun CountriesLoadedContent(
    countriesState: CountriesState.CountriesLoaded,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(countriesState.countries) { country ->
            CountryItem(
                country = country,
                modifier = Modifier.fillMaxWidth()
            ) {
                navHostController.navigate("exportsImports/${country.id}")
            }
        }
    }
}

@Composable
fun CountryItem(
    country: UiCountry,
    modifier: Modifier = Modifier,
    onClick: (UiCountry) -> Unit,
) {
    val itemHeight = 80.dp
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
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
                .height(itemHeight)
                .clickable { onClick(country) },
        )
    }
}

@Suppress("MagicNumber")
@Composable
fun TextWithDimmedBg(text: String, modifier: Modifier = Modifier) {
    GradientBg(
        gradientBrush = Brush.verticalGradient(
            0F to Transparent,
            0.8F to DimmedBlack,
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

@Suppress("MagicNumber")
@Preview
@Composable
fun PreviewGradientBg() {
    CountriesTheme {
        GradientBg(
            gradientBrush = Brush.verticalGradient(
                0F to Transparent,
                0.8F to DimmedBlack,
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
        ) {}
    }
}
