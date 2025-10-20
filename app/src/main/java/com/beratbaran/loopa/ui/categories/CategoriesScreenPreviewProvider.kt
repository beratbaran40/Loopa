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
                    CategoryModel(
                        category = "Wildlife",
                        categoryIconRes = R.drawable.ic_wildlife,
                    ),
                    CategoryModel(
                        category = "City Landmark",
                        categoryIconRes = R.drawable.ic_landmark,
                    ),
                    CategoryModel(
                        category = "Mountain",
                        categoryIconRes = R.drawable.ic_mountain,
                    ),
                    CategoryModel(
                        category = "Island/Beach",
                        categoryIconRes = R.drawable.ic_island,
                    ),
                    CategoryModel(
                        category = "Religious Site",
                        categoryIconRes = R.drawable.ic_religious_site,
                    ),
                    CategoryModel(
                        category = "Historical Site",
                        categoryIconRes = R.drawable.ic_historical_site,
                    ),
                    CategoryModel(
                        category = "National Park",
                        categoryIconRes = R.drawable.ic_national_park,
                    ),
                    CategoryModel(
                        category = "Natural Wonder",
                        categoryIconRes = R.drawable.ic_natural_wonder,
                    ),
                    CategoryModel(
                        category = "Cultural Heritage",
                        categoryIconRes = R.drawable.ic_cultural_heritage,
                    ),
                    CategoryModel(
                        category = "Archaeological Site",
                        categoryIconRes = R.drawable.ic_archaeological_site,
                    ),
                )
            ),
        )
}