package com.beratbaran.loopa.ui.categories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class CategoriesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesContract.UiState())
    val uiState: StateFlow<CategoriesContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<CategoriesContract.UiEffect>() }
    val uiEffect: Flow<CategoriesContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        _uiState.value = CategoriesContract.UiState(
            categories = loopaCategories(),
        )
    }

    fun onAction(uiAction: CategoriesContract.UiAction) {
        when (uiAction) {
            CategoriesContract.UiAction.OnCategoryClick -> {
                _uiEffect.trySend(CategoriesContract.UiEffect.NavigateToCategoryDetails)
            }
        }
    }
}