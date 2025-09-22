package com.beratbaran.loopa.ui.homepage

object HomepageContract {

    data class UiState(
        val name: String = "",
        val topPlaces: List<PlaceModel> = emptyList(),
        val wannaLookPlaces: List<PlaceModel> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnDetailsClick : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToDetails : UiEffect
    }
}