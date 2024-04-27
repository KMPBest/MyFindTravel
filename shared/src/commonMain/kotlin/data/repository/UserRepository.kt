package data.repository

import data.BackgroundExecutor
import data.source.remote.apiService.UserApiService
import kotlinx.coroutines.flow.MutableStateFlow

class UserRepository(
    private val userApiService: UserApiService,
    private val backgroundExecutor: BackgroundExecutor
) {
//    private val currentUserSubscriptionFlow = MutableStateFlow<Subscri>(null)
}