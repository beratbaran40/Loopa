package com.beratbaran.loopa.ui.homepage

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomepageViewModel : BaseViewModel<HomepageContract.UiEffect>() {

    private val _uiState = MutableStateFlow(HomepageContract.UiState())
    val uiState: StateFlow<HomepageContract.UiState> = _uiState.asStateFlow()

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnDetailsClick -> setEffect(HomepageContract.UiEffect.NavigateToDetails)
            UiAction.ToggleFavorite -> setEffect(HomepageContract.UiEffect.NavigateToFavorites)
        }
    }
}