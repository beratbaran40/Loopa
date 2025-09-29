package com.beratbaran.loopa.ui.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class DetailsScreenPreviewProvider : PreviewParameterProvider<DetailsContract.UiState> {
    override val values: Sequence<DetailsContract.UiState>
        get() = sequenceOf(
            DetailsContract.UiState(
                placeName = "Taj Mahal",
                placeRating = "4.6",
                placeLocation = "Location",
                placeLocationName = "India",
                placeCategory = "Category",
                placeCategoryName = "Landmark",
                placeImageUrl = "Image Url",
                isFavorite = false,
                isLoading = false,
            ),
            DetailsContract.UiState(
                placeName = "Taj Mahal",
                placeRating = "4.6",
                placeLocation = "Location",
                placeLocationName = "India",
                placeCategory = "Category",
                placeCategoryName = "Landmark",
                placeImageUrl = "Image Url",
                isFavorite = true,
                isLoading = false,
            ),
            DetailsContract.UiState(
                placeName = "Taj Mahal",
                placeRating = "4.6",
                placeLocation = "Location",
                placeLocationName = "India",
                placeCategory = "Category",
                placeCategoryName = "Landmark",
                placeImageUrl = "Image Url",
                isFavorite = true,
                isLoading = true,
            ),
        )
}