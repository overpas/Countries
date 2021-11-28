package by.overpass.countries.redux

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface Middleware<S : State, A : Action> {

    suspend fun processAction(state: S, action: A): Flow<A>
}

inline fun <reified S : State, reified A : Action> middleware(
    crossinline processAction: suspend (S, A) -> A
): Middleware<S, A> = object : Middleware<S, A> {
    override suspend fun processAction(state: S, action: A): Flow<A> =
        flowOf(processAction(state, action))
}
