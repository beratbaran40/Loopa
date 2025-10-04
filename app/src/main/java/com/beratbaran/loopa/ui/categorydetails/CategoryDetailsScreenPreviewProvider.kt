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
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = false
                    ),
                    PlaceModel(
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = true
                    ),
                    PlaceModel(
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = false
                    ),
                    PlaceModel(
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = true
                    ),
                    PlaceModel(
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = false
                    ),
                    PlaceModel(
                        name = "Kız Kulesi",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "imageUrl",
                        rating = "4.2",
                        isFavorite = true
                    ),
                )
            )
        )
}