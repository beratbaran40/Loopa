package com.beratbaran.loopa.ui.search

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beratbaran.loopa.ui.homepage.PlaceModel

class SearchScreenPreviewProvider : PreviewParameterProvider<SearchContract.UiState> {
    override val values: Sequence<SearchContract.UiState>
        get() = sequenceOf(
            SearchContract.UiState(
                isLoading = true,
            ),
            SearchContract.UiState(
                isLoading = false,
                places = listOf(
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Location",
                        imageUrl = "Image",
                        rating = 4.5,
                        isFavorite = false,
                        id = 1,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Location",
                        imageUrl = "Image",
                        rating = 4.5,
                        isFavorite = true,
                        id = 2,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Location",
                        imageUrl = "Image",
                        rating = 4.5,
                        isFavorite = false,
                        id = 3,
                        description = "açıklama",
                        categoryId = 1
                    ),
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Location",
                        imageUrl = "Image",
                        rating = 4.5,
                        isFavorite = true,
                        id = 4,
                        description = "açıklama",
                        categoryId = 1

                    ),
                )
            )
        )
}