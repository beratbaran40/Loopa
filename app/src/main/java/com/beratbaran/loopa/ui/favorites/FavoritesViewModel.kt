package com.beratbaran.loopa.ui.favorites

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiAction
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiEffect
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiState


class FavoritesViewModel : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override suspend fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnDetailsClick -> {
                setEffect(UiEffect.NavigateToDetails)
            }

            UiAction.OnUnFavoriteClick -> Unit
        }
    }
}