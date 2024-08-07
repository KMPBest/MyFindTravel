package data.source.remote.apiService

import data.source.remote.response.ApiResponse
import data.source.remote.response.TopFlightPricesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FlightsApiService(private val httpClient: HttpClient) {
  suspend fun getTop5Flights(
    origin: String?,
    maxPrice: String,
    sortBy: String,
  ): ApiResponse<TopFlightPricesResponse> =
    httpClient.get(ApiEndPoints.top5Flights) {
      url {
        parameters.append("sort", sortBy)
        origin?.let { parameters.append("origin", origin) }

        parameters.append("maxPrice", maxPrice)
      }
    }.body()
}
