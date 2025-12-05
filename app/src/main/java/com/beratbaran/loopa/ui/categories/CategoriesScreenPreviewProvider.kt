package com.beratbaran.loopa.ui.categories

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beratbaran.loopa.R

class CategoriesScreenPreviewProvider : PreviewParameterProvider<CategoriesContract.UiState> {
    override val values: Sequence<CategoriesContract.UiState>
        get() = sequenceOf(
            CategoriesContract.UiState(
                isLoading = true,
            ),
            CategoriesContract.UiState(
                isLoading = false,
                categories = listOf(
                    CategoryUiModel(
                        categoryId = 1,
                        category = "Wildlife",
                        categoryIconRes = R.drawable.ic_wildlife,
                    ),
                    CategoryUiModel(
                        categoryId = 2,
                        category = "City Landmark",
                        categoryIconRes = R.drawable.ic_landmark,
                    ),
                    CategoryUiModel(
                        categoryId = 3,
                        category = "Mountain",
                        categoryIconRes = R.drawable.ic_mountain,
                    ),
                    CategoryUiModel(
                        categoryId = 4,
                        category = "Island/Beach",
                        categoryIconRes = R.drawable.ic_island,
                    ),
                    CategoryUiModel(
                        categoryId = 5,
                        category = "Religious Site",
                        categoryIconRes = R.drawable.ic_religious_site,
                    ),
                    CategoryUiModel(
                        categoryId = 6,
                        category = "Historical Site",
                        categoryIconRes = R.drawable.ic_historical_site,
                    ),
                    CategoryUiModel(
                        categoryId = 7,
                        category = "National Park",
                        categoryIconRes = R.drawable.ic_national_park,
                    ),
                    CategoryUiModel(
                        categoryId = 8,
                        category = "Natural Wonder",
                        categoryIconRes = R.drawable.ic_natural_wonder,
                    ),
                    CategoryUiModel(
                        categoryId = 9,
                        category = "Cultural Heritage",
                        categoryIconRes = R.drawable.ic_cultural_heritage,
                    ),
                    CategoryUiModel(
                        categoryId = 10,
                        category = "Archaeological Site",
                        categoryIconRes = R.drawable.ic_archaeological_site,
                    ),
                )
            ),
        )
}