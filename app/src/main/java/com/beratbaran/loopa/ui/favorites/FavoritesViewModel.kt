package com.beratbaran.loopa.ui.favorites

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiAction
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiEffect
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiState


class FavoritesViewModel : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override fun handleAction(action: UiAction) {
        when (action) {
            is UiAction.OnDetailsClick -> {
                setEffect(UiEffect.NavigateToDetails(action.placeId))
            }

            UiAction.OnUnFavoriteClick -> Unit
        }
    }
}