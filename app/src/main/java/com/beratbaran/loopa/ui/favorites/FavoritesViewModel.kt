package com.beratbaran.loopa.ui.favorites

import com.beratbaran.loopa.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel : BaseViewModel<FavoritesContract.UiEffect>() {

    private val _uiState = MutableStateFlow(FavoritesContract.UiState())
    val uiState: StateFlow<FavoritesContract.UiState> = _uiState.asStateFlow()

    fun onAction(uiAction: FavoritesContract.UiAction) {
        when (uiAction) {
            FavoritesContract.UiAction.OnDetailsClick -> {
                setEffect(FavoritesContract.UiEffect.NavigateToDetails)
            }

            FavoritesContract.UiAction.OnUnFavoriteClick -> Unit
        }
    }
}