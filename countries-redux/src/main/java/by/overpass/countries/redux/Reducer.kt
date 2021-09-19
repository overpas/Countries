package by.overpass.countries.redux

interface Reducer<S : State, A : Action> {

    fun reduce(state: S, action: A): S
}

inline fun <reified S : State, reified A : Action> reducer(
    crossinline reduce: (S, A) -> S
): Reducer<S, A> = object : Reducer<S, A> {
    override fun reduce(state: S, action: A): S = reduce(state, action)
}