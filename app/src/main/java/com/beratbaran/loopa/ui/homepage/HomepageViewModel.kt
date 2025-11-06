package com.beratbaran.loopa.ui.homepage

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiState
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiEffect

class HomepageViewModel : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnDetailsClick -> setEffect(UiEffect.NavigateToDetails)
            UiAction.ToggleFavorite -> setEffect(UiEffect.NavigateToFavorites)
        }
    }
}