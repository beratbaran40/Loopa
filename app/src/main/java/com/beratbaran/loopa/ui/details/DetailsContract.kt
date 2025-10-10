package com.beratbaran.loopa.ui.details

object DetailsContract {
    data class UiState(
        val placeName: String = "",
        val placeRating: String = "",
        val placeLocation: String = "",
        val placeLocationName: String = "",
        val placeDescription: String = "",
        val placeCategory: String = "",
        val placeCategoryName: String = "",
        val placeImageUrl: String = "",
        val isFavorite: Boolean = false,
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object ShowOnMapClick : UiAction
        data object OnBackClick : UiAction
        data object ToggleFavorite : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToMaps : UiEffect
        data object NavigateToBack : UiEffect
    }
}

