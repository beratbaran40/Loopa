package com.beratbaran.loopa.ui.categorydetails

import com.beratbaran.loopa.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryDetailsViewModel : BaseViewModel<CategoryDetailsContract.UiEffect>() {
    private val _uiState = MutableStateFlow(CategoryDetailsContract.UiState())
    val uiState: StateFlow<CategoryDetailsContract.UiState> = _uiState.asStateFlow()

    fun onAction(action: CategoryDetailsContract.UiAction) {
        when (action) {
            CategoryDetailsContract.UiAction.OnDetailsClick -> {
                setEffect(CategoryDetailsContract.UiEffect.NavigateToDetails)
            }

            CategoryDetailsContract.UiAction.OnToggleFavorite -> {
                _uiState.value = _uiState.value.copy(isFavorite = !_uiState.value.isFavorite)
            }

            CategoryDetailsContract.UiAction.OnBackClick -> {
                setEffect(CategoryDetailsContract.UiEffect.NavigateToBack)
            }
        }
    }
}