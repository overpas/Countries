package by.overpass.countries.economies.ui.home

import by.overpass.countries.redux.Middleware

class HomeMiddleware : Middleware<HomeState, HomeAction> {

    override suspend fun processAction(state: HomeState, action: HomeAction): HomeAction = action
}