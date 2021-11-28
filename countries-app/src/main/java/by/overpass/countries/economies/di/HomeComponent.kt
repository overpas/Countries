/**
 * Home Component
 */

package by.overpass.countries.economies.di

import by.overpass.countries.economies.ui.home.HomeAction
import by.overpass.countries.economies.ui.home.HomeMiddleware
import by.overpass.countries.economies.ui.home.HomeReducer
import by.overpass.countries.economies.ui.home.HomeState
import by.overpass.countries.redux.Middleware
import by.overpass.countries.redux.Reducer
import by.overpass.countries.redux.Store
import by.overpass.countries.redux.android.SimpleAndroidViewModelStore

interface HomeComponent {

    fun homeStore(
        homeMiddleware: Middleware<HomeState, HomeAction> = homeMiddleware(),
        homeReducer: Reducer<HomeState, HomeAction> = homeReducer(),
    ): Store<HomeState, HomeAction>

    fun homeMiddleware(): Middleware<HomeState, HomeAction>

    fun homeReducer(): Reducer<HomeState, HomeAction>
}

object RealHomeComponent : HomeComponent {

    override fun homeStore(
        homeMiddleware: Middleware<HomeState, HomeAction>,
        homeReducer: Reducer<HomeState, HomeAction>
    ): Store<HomeState, HomeAction> = SimpleAndroidViewModelStore(
        homeMiddleware,
        homeReducer,
        HomeState.initial(),
    )

    override fun homeMiddleware(): Middleware<HomeState, HomeAction> = HomeMiddleware()

    override fun homeReducer(): Reducer<HomeState, HomeAction> = HomeReducer()
}