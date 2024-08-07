package data.source.remote.apiService

import data.source.remote.response.ApiResponse
import data.source.remote.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post

class UserApiService(private val httpClient: HttpClient) {
  suspend fun createOrGetUser(): ApiResponse<UserResponse> = httpClient.post(ApiEndPoints.users).body()
}
