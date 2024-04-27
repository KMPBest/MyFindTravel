package root

import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import di.appModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import utils.logging.AppLogger
import utils.logging.Logger

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
        AppLogger.e("[INITIAL GOOGLE")
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = "630844940987-rg3d5f5rt92pm6q2qom6c4i3iptf5s05.apps.googleusercontent.com"))
    }
}