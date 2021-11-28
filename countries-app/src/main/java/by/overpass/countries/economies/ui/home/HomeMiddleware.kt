package by.overpass.countries.economies.ui.home

import by.overpass.countries.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeMiddleware : Middleware<HomeState, HomeAction> {

    override suspend fun processAction(state: HomeState, action: HomeAction): Flow<HomeAction> =
        flowOf(action)
}