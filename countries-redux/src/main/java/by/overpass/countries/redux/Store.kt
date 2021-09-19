package by.overpass.countries.redux

import kotlinx.coroutines.flow.StateFlow

interface Store<out S : State, in A : Action> {

    val state: StateFlow<S>

    fun dispatch(action: A)
}