package com.beratbaran.loopa.ui.categories

object CategoriesContract {
    data class UiState(
        val categories: List<CategoryUiModel> = emptyList(),
        val isLoading: Boolean = false,
    )

    sealed interface UiAction {
        data class OnCategoryClick(val categoryId: Int) : UiAction
    }

    sealed interface UiEffect {
        data class NavigateToCategoryDetails(val categoryId: Int) : UiEffect
    }
}