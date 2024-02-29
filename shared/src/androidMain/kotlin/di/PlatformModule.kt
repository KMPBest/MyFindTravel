package di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import utils.AndroidAppVersion
import utils.AppVersion

internal actual val platformModule: Module = module {
    factoryOf(::AndroidAppVersion) bind AppVersion::class
}