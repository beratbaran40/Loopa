package com.beratbaran.loopa.ui.categories

import com.beratbaran.loopa.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriesViewModel : BaseViewModel<CategoriesContract.UiEffect>() {

    private val _uiState = MutableStateFlow(CategoriesContract.UiState())
    val uiState: StateFlow<CategoriesContract.UiState> = _uiState.asStateFlow()

    init {
        _uiState.value = CategoriesContract.UiState(
            categories = loopaCategories(),
        )
    }

    fun onAction(uiAction: CategoriesContract.UiAction) {
        when (uiAction) {
            CategoriesContract.UiAction.OnCategoryClick -> {
                setEffect (CategoriesContract.UiEffect.NavigateToCategoryDetails)
            }
        }
    }
}