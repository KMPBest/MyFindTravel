package di

import data.di.dataModule
import org.koin.core.module.Module
import screens.di.screenModule

internal expect val platformModule: Module
val appModules: List<Module>
    get() = platformModule + dataModule + screenModule