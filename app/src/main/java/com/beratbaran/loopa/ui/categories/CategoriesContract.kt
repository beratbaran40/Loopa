package com.beratbaran.loopa.ui.categories

object CategoriesContract {
    data class UiState(
        val categories: List<CategoryModel> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data object OnCategoryClick : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToCategoryDetails : UiEffect
    }
}