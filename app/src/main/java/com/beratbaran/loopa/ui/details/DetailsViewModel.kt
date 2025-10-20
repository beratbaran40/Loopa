package com.beratbaran.loopa.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class DetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsContract.UiState())
    val uiState: StateFlow<DetailsContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<DetailsContract.UiEffect>() }
    val uiEffect: Flow<DetailsContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: DetailsContract.UiAction) {
        when (uiAction) {

            DetailsContract.UiAction.ShowOnMapClick -> {
                _uiEffect.trySend(DetailsContract.UiEffect.NavigateToMaps)
            }

            DetailsContract.UiAction.OnBackClick -> {
                _uiEffect.trySend(DetailsContract.UiEffect.NavigateToBack)
            }

            DetailsContract.UiAction.ToggleFavorite -> {
                _uiState.value = _uiState.value.copy(isFavorite = !_uiState.value.isFavorite)
            }
        }
    }
}