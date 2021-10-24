/**
 * Trade Flows UI
 */

package by.overpass.countries.feature.trade.flows

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import by.overpass.countries.redux.Store
import by.overpass.countries.ui.common.core.ContentLoading
import by.overpass.countries.ui.common.theme.CountriesTheme

@Composable
fun TradeFlows(
    tradeFlowsStore: Store<TradeFlowsState, TradeFlowsAction>,
    modifier: Modifier = Modifier,
) {
    val state: TradeFlowsState by tradeFlowsStore.state.collectAsState()
    LaunchedEffect(true) {
        tradeFlowsStore.dispatch(TradeFlowsAction.LoadExports)
    }
    TradeFlowsContent(state, modifier)
}

@Composable
fun TradeFlowsContent(tradeFlowsState: TradeFlowsState, modifier: Modifier = Modifier) {
    when (tradeFlowsState) {
        is TradeFlowsState.Loading -> ContentLoading(modifier)
        is TradeFlowsState.Error -> TODO()
        is TradeFlowsState.ExportsLoaded -> Exports(tradeFlowsState.exportsString, modifier)
    }
}

@Composable
fun Exports(exportsString: String, modifier: Modifier = Modifier) {
    Text(
        text = exportsString,
        modifier = modifier,
    )
}

@Preview
@Composable
fun PreviewExports() {
    CountriesTheme {
        Exports(
            exportsString = "exports",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
        )
    }
}
