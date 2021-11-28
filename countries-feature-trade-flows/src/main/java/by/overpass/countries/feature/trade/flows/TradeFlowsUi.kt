/**
 * Trade Flows UI
 */

package by.overpass.countries.feature.trade.flows

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.overpass.countries.redux.Store
import by.overpass.countries.ui.common.core.ContentLoading
import by.overpass.countries.ui.common.theme.CountriesTheme
import by.overpass.treemapchart.android.TreemapChart
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import by.overpass.treemapchart.core.tree.Tree
import by.overpass.treemapchart.core.tree.tree

@Composable
fun TradeFlows(
    tradeFlowsStore: Store<TradeFlowsState, TradeFlowsAction>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val state: TradeFlowsState by tradeFlowsStore.state.collectAsState()
    LaunchedEffect(true) {
        tradeFlowsStore.dispatch(TradeFlowsAction.LoadExports)
    }
    TradeFlowsContent(state, modifier) {
        navController.navigateUp()
    }
}

@Composable
fun TradeFlowsContent(
    tradeFlowsState: TradeFlowsState,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    when (tradeFlowsState) {
        is TradeFlowsState.Loading -> ContentLoading(modifier)
        is TradeFlowsState.LoadingPartOfExports -> ExportsLoading(tradeFlowsState.message, modifier)
        is TradeFlowsState.Error -> TODO()
        is TradeFlowsState.ExportsLoaded -> Exports(tradeFlowsState, modifier, onBackClicked)
    }
}

@Composable
fun ExportsLoading(message: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = message)
        }
    }
}

@Composable
fun Exports(
    exportsLoaded: TradeFlowsState.ExportsLoaded,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .clickable { onBackClicked() }
                        .padding(10.dp),
                )
                Text(text = "${exportsLoaded.countryName} > Exports")
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        ExportsChart(
            exports = exportsLoaded.exports,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ExportsChart(
    exports: Tree<UiExport>,
    modifier: Modifier = Modifier,
) {
    TreemapChart(
        data = exports,
        evaluateItem = { it.value },
        treemapChartMeasurer = remember { SquarifiedMeasurer() },
        modifier = modifier
    ) {
        ExportItem(it)
    }
}

@Composable
fun ExportItem(export: UiExport, modifier: Modifier = Modifier) {
    Surface(
        color = export.color,
        modifier = modifier
            .border(1.dp, MaterialTheme.colors.onSurface),
    ) {
        Box(contentAlignment = Alignment.Center) {
            var showText by remember { mutableStateOf(true) }
            if (showText) {
                Text(
                    text = export.name,
                    textAlign = TextAlign.Center,
                    onTextLayout = { textResult ->
                        if (textResult.didOverflowHeight || textResult.didOverflowWidth) {
                            showText = false
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewExportsChart() {
    CountriesTheme {
        ExportsChart(
            exports = tree(
                UiExport(2.0, Color.White, "all"),
            ) {
                node(UiExport(1.0, Color.Blue, "one"))
                node(UiExport(1.0, Color.Red, "two"))
            },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun PreviewExportsLoading() {
    CountriesTheme {
        ExportsLoading(message = "Loading exports...")
    }
}