package navigation

import data.repository.GlobalAppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.UiStateHolder
import utils.uiStateHolderScope

class RootUiStateHolder(private val globalAppRepository: GlobalAppRepository): UiStateHolder() {
    private val _uiState = MutableStateFlow(RootScreenUiState(isAppVersionUpgradeRequired = false))
    val uiState = _uiState.asStateFlow()

    init {
    }

    private fun getGlobalConfig() = uiStateHolderScope.launch {


    }
}