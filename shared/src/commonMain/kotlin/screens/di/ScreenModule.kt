package screens.di

import navigation.RootUiStateHolder
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import screens.home.HomeUiStateHolder
import screens.topFlights.TopFlightsUiStateHolder

val screenModule = module {
    factoryOf(::RootUiStateHolder)
    factoryOf(::HomeUiStateHolder)
    factoryOf(::TopFlightsUiStateHolder)
//    factoryOf()
}