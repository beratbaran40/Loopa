package com.beratbaran.loopa.ui.homepage

object HomepageContract {
    data class UiState(
        val name: String = "",
        val topPlaces: List<PlaceModel> = emptyList(),
        val wantToLookPlaces: List<PlaceModel> = emptyList(),
        val isFavorite: Boolean = false,
        val isLoading: Boolean = false,
        val error: String? = null,
    )

    sealed interface UiAction {
        data class OnDetailsClick(val placeId: Int) : UiAction
        data object ToggleFavorite : UiAction
    }

    sealed interface UiEffect {
        data class NavigateToDetails(val placeId: Int) : UiEffect
        data object NavigateToFavorites : UiEffect
    }
}