package by.overpass.countries.redux

interface Middleware<S : State, A : Action> {

    suspend fun processAction(state: S, action: A): A
}

inline fun <reified S : State, reified A : Action> middleware(
    crossinline processAction: suspend (S, A) -> A
): Middleware<S, A> = object : Middleware<S, A> {
    override suspend fun processAction(state: S, action: A): A = processAction(state, action)
}
