package com.beratbaran.loopa.ui.categorydetails

import com.beratbaran.loopa.ui.base.BaseViewModel

class CategoryDetailsViewModel :
    BaseViewModel<CategoryDetailsContract.UiState, CategoryDetailsContract.UiEffect>(
        initialState = CategoryDetailsContract.UiState()
    ) {
    fun onAction(action: CategoryDetailsContract.UiAction) {
        when (action) {
            CategoryDetailsContract.UiAction.OnDetailsClick -> {
                setEffect(CategoryDetailsContract.UiEffect.NavigateToDetails)
            }

            CategoryDetailsContract.UiAction.OnToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }

            CategoryDetailsContract.UiAction.OnBackClick -> {
                setEffect(CategoryDetailsContract.UiEffect.NavigateToBack)
            }
        }
    }
}