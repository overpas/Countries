package by.overpass.countries.redux.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import by.overpass.countries.redux.Action
import by.overpass.countries.redux.Middleware
import by.overpass.countries.redux.Reducer
import by.overpass.countries.redux.State
import by.overpass.countries.redux.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

public open class SimpleAndroidViewModelStore<S : State, A : Action>(
    private val middleware: Middleware<S, A>,
    private val reducer: Reducer<S, A>,
    initialState: S,
) : ViewModel(), Store<S, A> {

    private val stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val state: StateFlow<S> = stateFlow.asStateFlow()

    override fun dispatch(action: A) {
        viewModelScope.launch {
            flowOf(action)
                .flatMapMerge { middleware.processAction(stateFlow.value, it) }
                .collect { action ->
                    stateFlow.emit(reducer.reduce(stateFlow.value, action))
                }
        }
    }
}

/**
 * This is android implementation. Will be expect/actual in future
 *
 * @param createStore store factory
 * @return a ready-to-use scoped [Store] instance
 */
@Composable
public fun <S : State, A : Action> store(
    createStore: () -> Store<S, A>
): Store<S, A> = viewModel(
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = createStore() as T
    }
)