package com.beratbaran.loopa.ui.favorites

object FavoritesContract {
    data class UiState(
        val isLoading: Boolean = false,
        val favorites: List<FavoritesModel> = emptyList(),
    )

    sealed class UiEffect {
        data object NavigateToDetails : UiEffect()
        data object NavigateToBack : UiEffect()
    }

    sealed class UiAction {
        data object OnDetailsClick : UiAction()
        data object OnBackClick : UiAction()
    }
}

