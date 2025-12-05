package com.beratbaran.loopa.ui.favorites

import com.beratbaran.loopa.ui.homepage.PlaceModel

object FavoritesContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favorites: List<PlaceModel> = emptyList(),
    )

    sealed class UiEffect {
        data class NavigateToDetails(val placeId: Int) : UiEffect()
    }

    sealed class UiAction {
        data class OnDetailsClick(val placeId: Int) : UiAction()
        data object OnUnFavoriteClick : UiAction()
    }
}