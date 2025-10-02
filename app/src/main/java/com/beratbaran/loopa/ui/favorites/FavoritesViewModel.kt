package com.beratbaran.loopa.ui.favorites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class FavoritesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesContract.UiState())
    val uiState: StateFlow<FavoritesContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<FavoritesContract.UiEffect>() }
    val uiEffect: Flow<FavoritesContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: FavoritesContract.UiAction) {
        when (uiAction) {
            FavoritesContract.UiAction.OnDetailsClick -> {
                _uiEffect.trySend(FavoritesContract.UiEffect.NavigateToDetails)
            }

            FavoritesContract.UiAction.OnUnFavoriteClick -> {
                _uiState.value = _uiState.value.copy(isFavorite = !_uiState.value.isFavorite)
            }
        }
    }

}