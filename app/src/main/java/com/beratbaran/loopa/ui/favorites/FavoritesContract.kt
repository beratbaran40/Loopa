package com.beratbaran.loopa.ui.favorites

object FavoritesContract {
    data class UiState(
        val isFavorite: Boolean = true,
        val isLoading: Boolean = false,
        val favoriteItem: List<FavoritesModel> = emptyList(),
    )

    sealed class UiEffect {
        data object NavigateToDetails : UiEffect()
    }

    sealed class UiAction {
        data object OnDetailsClick : UiAction()
        data object OnUnFavoriteClick : UiAction()
    }
}

