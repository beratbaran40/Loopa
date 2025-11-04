package com.beratbaran.loopa.ui.categories

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiAction
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiEffect
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiState

class CategoriesViewModel : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    init {
        setState {
            copy(
                categories = loopaCategories()
            )
        }
    }

    override suspend fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnCategoryClick -> {
                setEffect(UiEffect.NavigateToCategoryDetails)
            }
        }
    }
}