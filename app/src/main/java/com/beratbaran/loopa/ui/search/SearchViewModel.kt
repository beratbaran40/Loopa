package com.beratbaran.loopa.ui.search

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.search.SearchContract.UiAction
import com.beratbaran.loopa.ui.search.SearchContract.UiEffect
import com.beratbaran.loopa.ui.search.SearchContract.UiState

class SearchViewModel :
    BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override fun handleAction(action: UiAction) {
        when (action) {

            is UiAction.OnRandomPlaceClick ->
                setEffect(UiEffect.NavigateToRandomPlace)

            is UiAction.OnDetailsClick ->
                setEffect(UiEffect.NavigateToDetails)

            is UiAction.ToggleFavorite ->
                setState { copy(isFavorite = !isFavorite) }

            is UiAction.OnQueryChange -> {
                setState { copy(query = action.query) }
            }

            UiAction.ClearQuery -> {
                setState { copy(query = "") }
            }
        }
    }
}