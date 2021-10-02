@file:Suppress("FILE_UNORDERED_IMPORTS", "HEADER_MISSING_IN_NON_SINGLE_CLASS_FILE")

package by.overpass.countries.redux.android

import by.overpass.countries.redux.Action
import by.overpass.countries.redux.State
import by.overpass.countries.redux.middleware
import by.overpass.countries.redux.reducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SimpleAndroidViewModelStoreTest {

    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val middleware = middleware { state: TestState, action: TestAction -> action }
    private val reducer = reducer { state: TestState, action: TestAction ->
        when (state) {
            is TestState.One -> TestState.Two
            else -> TestState.One
        }
    }
    private val store = SimpleAndroidViewModelStore(
        middleware,
        reducer,
        TestState.One,
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `action is dispatched`() = testCoroutineDispatcher.runBlockingTest {
        assertEquals(TestState.One, store.state.first())

        store.dispatch(TestAction)

        assertEquals(TestState.Two, store.state.first())
    }
}

private object TestAction : Action
private sealed class TestState : State {
    object One : TestState()
    object Two : TestState()
}