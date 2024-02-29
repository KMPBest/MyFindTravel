package root

import di.appModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import utils.logging.AppLogger

object AppInitializer{
    fun initialize(isDebug: Boolean = false, onKoinStart: KoinApplication.() -> Unit){
        startKoin {
            AppLogger.d("ON START KOIN 1")
            onKoinStart()
            modules(appModules)
            onApplicationStart()
            AppLogger.d("ON START KOIN 2")
        }
        if(isDebug) AppLogger.initialize()
    }

    private fun KoinApplication.onApplicationStart() {

    }
}