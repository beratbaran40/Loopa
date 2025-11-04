package com.beratbaran.loopa.ui.categorydetails

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiAction
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiEffect
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsContract.UiState


class CategoryDetailsViewModel :
    BaseViewModel<UiState, UiAction, UiEffect>(
        initialState = UiState()
    ) {
    override suspend fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnDetailsClick -> {
                setEffect(UiEffect.NavigateToDetails)
            }

            UiAction.OnToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }

            UiAction.OnBackClick -> {
                setEffect(UiEffect.NavigateToBack)
            }
        }
    }
}