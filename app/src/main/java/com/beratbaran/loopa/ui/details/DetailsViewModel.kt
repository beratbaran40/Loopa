package com.beratbaran.loopa.ui.details

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.details.DetailsContract.UiAction
import com.beratbaran.loopa.ui.details.DetailsContract.UiEffect
import com.beratbaran.loopa.ui.details.DetailsContract.UiState

class DetailsViewModel :
    BaseViewModel<UiState, UiAction, UiEffect>(
        initialState = UiState()
    ) {

    private fun onImageSelected(index: Int?) {
        setState { copy(selectedIndex = index) }
    }

    override suspend fun handleAction(action: UiAction) {
        when (action) {

            UiAction.ShowOnMapClick -> {
                setEffect(UiEffect.NavigateToMaps)
            }

            UiAction.OnBackClick -> {
                setEffect(UiEffect.NavigateToBack)
            }

            UiAction.ToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }

            is UiAction.OnImageSelected -> {
                onImageSelected(action.index)
            }
        }
    }
}