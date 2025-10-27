package com.beratbaran.loopa.ui.homepage

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction

class HomepageViewModel : BaseViewModel<HomepageContract.UiState, HomepageContract.UiEffect>(
    initialState = HomepageContract.UiState()
) {
    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnDetailsClick -> setEffect(HomepageContract.UiEffect.NavigateToDetails)
            UiAction.ToggleFavorite -> setEffect(HomepageContract.UiEffect.NavigateToFavorites)
        }
    }
}