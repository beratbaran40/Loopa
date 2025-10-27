package com.beratbaran.loopa.ui.favorites

import com.beratbaran.loopa.ui.base.BaseViewModel

class FavoritesViewModel : BaseViewModel<FavoritesContract.UiState, FavoritesContract.UiEffect>(
    initialState = FavoritesContract.UiState()
) {
    fun onAction(uiAction: FavoritesContract.UiAction) {
        when (uiAction) {
            FavoritesContract.UiAction.OnDetailsClick -> {
                setEffect(FavoritesContract.UiEffect.NavigateToDetails)
            }

            FavoritesContract.UiAction.OnUnFavoriteClick -> Unit
        }
    }
}
