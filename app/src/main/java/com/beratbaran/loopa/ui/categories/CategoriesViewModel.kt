package com.beratbaran.loopa.ui.categories

import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiAction
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiEffect
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiState
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    init {
        setState {
            copy(
                categories = loopaCategories()
            )
        }
    }

    override fun handleAction(action: UiAction) {
        when (action) {
            is UiAction.OnCategoryClick -> {
                setEffect(UiEffect.NavigateToCategoryDetails(action.categoryId))
            }
        }
    }
}