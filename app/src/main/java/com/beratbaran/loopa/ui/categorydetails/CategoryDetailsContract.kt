package com.beratbaran.loopa.ui.categorydetails

import com.beratbaran.loopa.ui.homepage.PlaceModel

object CategoryDetailsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val isFavorite: Boolean = false,
        val places: List<PlaceModel> = emptyList(),
        val numberOfPlaces: Int = 0,
    )

    sealed interface UiAction {
        data object OnDetailsClick : UiAction
        data object OnBackClick : UiAction
        data object OnToggleFavorite : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToDetails : UiEffect
        data object NavigateToBack : UiEffect
    }
}