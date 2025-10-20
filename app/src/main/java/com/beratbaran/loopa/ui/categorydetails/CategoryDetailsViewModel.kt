package com.beratbaran.loopa.ui.categorydetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class CategoryDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryDetailsContract.UiState())
    val uiState: StateFlow<CategoryDetailsContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<CategoryDetailsContract.UiEffect>() }
    val uiEffect: Flow<CategoryDetailsContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: CategoryDetailsContract.UiAction) {
        when (action) {
            CategoryDetailsContract.UiAction.OnDetailsClick -> {
                _uiEffect.trySend(CategoryDetailsContract.UiEffect.NavigateToDetails)
            }

            CategoryDetailsContract.UiAction.OnToggleFavorite -> {
                _uiState.value = _uiState.value.copy(isFavorite = !_uiState.value.isFavorite)
            }

            CategoryDetailsContract.UiAction.OnBackClick -> {
                _uiEffect.trySend(CategoryDetailsContract.UiEffect.NavigateToBack)
            }
        }
    }
}