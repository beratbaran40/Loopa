package com.beratbaran.loopa.ui.categories

import com.beratbaran.loopa.ui.base.BaseViewModel

class CategoriesViewModel : BaseViewModel<CategoriesContract.UiState, CategoriesContract.UiEffect>(
    initialState = CategoriesContract.UiState()
) {
    init {
        setState {
            copy(
                categories = loopaCategories()
            )
        }
    }

    fun onAction(uiAction: CategoriesContract.UiAction) {
        when (uiAction) {
            CategoriesContract.UiAction.OnCategoryClick -> {
                setEffect(CategoriesContract.UiEffect.NavigateToCategoryDetails)
            }
        }
    }
}