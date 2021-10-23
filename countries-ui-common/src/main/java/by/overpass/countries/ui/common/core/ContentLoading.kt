/**
 * Common Loading UI
 */

package by.overpass.countries.ui.common.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.overpass.countries.ui.common.theme.CountriesTheme

@Composable
fun ContentLoading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        CircularProgressIndicator(modifier)
    }
}

@Preview
@Composable
fun PreviewContentLoading() {
    CountriesTheme {
        ContentLoading()
    }
}