package data.repository

import data.BackgroundExecutor
import data.source.remote.apiService.UserApiService

class UserRepository(
  private val userApiService: UserApiService,
  private val backgroundExecutor: BackgroundExecutor,
) {
//    private val currentUserSubscriptionFlow = MutableStateFlow<Subscri>(null)
}
