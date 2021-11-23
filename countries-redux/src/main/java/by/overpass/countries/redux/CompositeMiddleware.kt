package by.overpass.countries.redux

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

class CompositeMiddleware<S : State, A : Action>(
    private val middlewares: List<Middleware<S, A>>,
) : Middleware<S, A> {

    override suspend fun processAction(state: S, action: A): Flow<A> {
        var actionFlow = flowOf(action)
        middlewares.forEach { middleware ->
            actionFlow =
                actionFlow.flatMapMerge { action -> middleware.processAction(state, action) }
        }
        return actionFlow
    }
}