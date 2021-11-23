package by.overpass.countries.redux

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

object TestState : State

sealed class TestAction : Action {
    object One : TestAction()
    object Two : TestAction()
    object Three : TestAction()
}

class CompositeMiddlewareTest {

    @Test
    fun `action is processed by all middlewares`() = runBlocking {
        val compositeMiddleware = CompositeMiddleware(
            listOf(
                middleware { state, action ->
                    if (action is TestAction.One) {
                        TestAction.Two
                    } else {
                        action
                    }
                },
                middleware { state, action ->
                    if (action is TestAction.Two) {
                        TestAction.Three
                    } else {
                        action
                    }
                },
            )
        )

        val actions = compositeMiddleware.processAction(TestState, TestAction.One).toList()
        assertEquals(TestAction.Three, actions[0])
    }
}