package data.di

import data.repository.FlightsRepository
import data.repository.GlobalAppRepository
import data.repository.UserRepository
import data.source.remote.apiService.FlightsApiService
import data.source.remote.apiService.GlobalApiService
import data.source.remote.apiService.UserApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import secrets.BuildConfig
import utils.logging.AppLogger
import kotlin.coroutines.CoroutineContext

private val remoteSourceModule =
  module {
    single {
      HttpClient {
        defaultRequest {
          url {
            protocol = URLProtocol.HTTPS
            host = "api.findtravelnow.com"
            parameters.append("api_key", BuildConfig.API_KEY)
          }
          header(HttpHeaders.ContentType, "application/json")
        }
        install(Logging) {
          logger =
            object : Logger {
              override fun log(message: String) {
                AppLogger.d("NetworkRequest: $message")
              }
            }
          level = LogLevel.ALL
        }
        install(ContentNegotiation) {
          json(Json { ignoreUnknownKeys = true })
        }
      }.also {
//            it.plugin(HttpSend).intercept { request ->
//                val firebaseUserIdToken = Firebase.auth.currentUser?.getIdToken(true)
//                request.header("Authorization", firebaseUserIdToken)
//                execute(request)
//            }
      }
    }
    factoryOf(::GlobalApiService)
    factoryOf(::FlightsApiService)
    factoryOf(::UserApiService)
  }

private val repositoryModule =
  module {

    factory { Dispatchers.IO } bind CoroutineContext::class
    factoryOf(::GlobalAppRepository)
    factoryOf(::FlightsRepository)
    singleOf(::UserRepository)
  }

val dataModule =
  module {
    includes(repositoryModule, remoteSourceModule)
  }
