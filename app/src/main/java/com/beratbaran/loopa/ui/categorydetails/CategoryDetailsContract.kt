package com.beratbaran.loopa.ui.categorydetails

import com.beratbaran.loopa.ui.homepage.PlaceModel

object CategoryDetailsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val isFavorite: Boolean = false,
        val places: List<PlaceModel> = emptyList(),
        val numberOfPlaces: Int =  0
        ,
    )

    sealed interface UiAction {
        data class OnDetailsClick(val placeId: Int) : UiAction
        data object OnBackClick : UiAction
        data object OnToggleFavorite : UiAction
    }

    sealed interface UiEffect {
        data class NavigateToDetails(val placeId: Int) : UiEffect
        data object NavigateToBack : UiEffect
    }
}