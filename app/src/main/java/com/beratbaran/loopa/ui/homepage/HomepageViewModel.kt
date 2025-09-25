package com.beratbaran.loopa.ui.homepage

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class HomepageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomepageContract.UiState())
    val uiState: StateFlow<HomepageContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<HomepageContract.UiEffect>() }
    val uiEffect: Flow<HomepageContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnDetailsClick -> {
                _uiEffect.trySend(HomepageContract.UiEffect.NavigateToDetails)
            }

            UiAction.ToggleFavorite -> {
                _uiEffect.trySend(HomepageContract.UiEffect.NavigateToFavorites)
            }
        }
    }
}