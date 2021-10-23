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
import by.overpass.countries.redux.Store
import by.overpass.countries.ui.common.core.ContentLoading

@Composable
fun TradeFlows(tradeFlowsStore: Store<TradeFlowsState, TradeFlowsAction>) {
    val state: TradeFlowsState by tradeFlowsStore.state.collectAsState()
    LaunchedEffect(true) {
        tradeFlowsStore.dispatch(TradeFlowsAction.LoadExports)
    }
    TradeFlowsContent(state)
}

@Composable
fun TradeFlowsContent(tradeFlowsState: TradeFlowsState) {
    when (tradeFlowsState) {
        is TradeFlowsState.Loading -> ContentLoading()
        is TradeFlowsState.Error -> TODO()
        is TradeFlowsState.ExportsLoaded -> Exports(tradeFlowsState.exportsString)
    }
}

@Composable
fun Exports(exportsString: String) {
    Text(
        text = exportsString,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    )
}
