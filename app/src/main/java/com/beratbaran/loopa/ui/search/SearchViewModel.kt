package com.beratbaran.loopa.ui.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SearchContract.UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<SearchContract.UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onAction(action: SearchContract.UiAction) {
        when (action) {

            is SearchContract.UiAction.OnRandomPlaceClick ->
                _uiEffect.trySend(SearchContract.UiEffect.NavigateToRandomPlace)

            is SearchContract.UiAction.OnDetailsClick ->
                _uiEffect.trySend(SearchContract.UiEffect.NavigateToDetails)

            is SearchContract.UiAction.ToggleFavorite ->
                _uiState.value = _uiState.value.copy(isFavorite = !_uiState.value.isFavorite)

            is SearchContract.UiAction.OnQueryChange -> {
                _uiState.value = _uiState.value.copy(query = action.query)
            }

            SearchContract.UiAction.ClearQuery -> {
                _uiState.value = _uiState.value.copy(query = "")
            }
        }
    }
}
