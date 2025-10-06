package com.beratbaran.loopa.ui.search

import com.beratbaran.loopa.ui.homepage.PlaceModel

object SearchContract {
    data class UiState(
        val isLoading: Boolean = false,
        val isFavorite: Boolean = false,
        val searchResultQuantity: Int = 0,
        val searchResultCountry: String = "",
        val places: List<PlaceModel> = emptyList(),
        val query: String = "",
    )

    sealed interface UiAction {
        data class OnDetailsClick(val placeId: String) : UiAction
        data class OnRandomPlaceClick(val placeId: String) : UiAction
        data class ToggleFavorite(val placeId: String) : UiAction
        data class OnQueryChange(val query: String) : UiAction
        data object ClearQuery : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToDetails : UiEffect
        data object NavigateToRandomPlace : UiEffect

    }
}