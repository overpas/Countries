package by.overpass.countries.economies

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
import kotlinx.coroutines.launch

class SimpleAndroidViewModelStore<S : State, A : Action>(
    private val middleware: Middleware<S, A>,
    private val reducer: Reducer<S, A>,
    initial: S,
) : ViewModel(), Store<S, A> {

    private val stateFlow: MutableStateFlow<S> = MutableStateFlow(initial)

    override val state: StateFlow<S>
        get() = stateFlow.asStateFlow()

    override fun dispatch(action: A) {
        viewModelScope.launch {
            val processedAction = middleware.processAction(stateFlow.value, action)
            val state = reducer.reduce(stateFlow.value, processedAction)
            stateFlow.emit(state)
        }
    }
}

/**
 * This is android implementation. Will be expect/actual in future
 */
@Composable
fun <S : State, A : Action> store(
    createStore: () -> Store<S, A>
): Store<S, A> = viewModel(
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return createStore() as T
        }
    }
)