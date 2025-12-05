package com.beratbaran.loopa.ui.categorydetails

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beratbaran.loopa.ui.homepage.PlaceModel

class CategoryDetailsScreenPreviewProvider :
    PreviewParameterProvider<CategoryDetailsContract.UiState> {
    override val values: Sequence<CategoryDetailsContract.UiState>
        get() = sequenceOf(
            CategoryDetailsContract.UiState(
                isLoading = true,
            ),
            CategoryDetailsContract.UiState(
                isLoading = false,
                places = listOf(
                    PlaceModel(
                        id = 0,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = false,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        id = 1,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = true,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        id = 2,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = false,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        id = 3,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = true,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        id = 4,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = false,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        id = 5,
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = 4.2,
                        isFavorite = true,
                        description = "açıklama",
                        categoryId = 1
                    ),
                )
            )
        )
}