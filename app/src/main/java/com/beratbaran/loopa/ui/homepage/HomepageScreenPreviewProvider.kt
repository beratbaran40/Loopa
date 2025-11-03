package com.beratbaran.loopa.ui.homepage

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class HomepageScreenPreviewProvider : PreviewParameterProvider<HomepageContract.UiState> {
    override val values: Sequence<HomepageContract.UiState>
        get() = sequenceOf(
            HomepageContract.UiState(
                isLoading = true,
            ),
            HomepageContract.UiState(
                isLoading = false,
                name = "Berat",
                topPlaces = listOf(
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Beyoğlu, İstanbul",
                        imageUrl = "",
                        rating = "4.7",
                        isFavorite = false,
                        id = 1
                    ),
                    PlaceModel(
                        name = "Maiden’s Tower",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "",
                        rating = "4.6",
                        isFavorite = true,
                        id = 2
                    ),
                    PlaceModel(
                        name = "Yerebatan Sarnıcı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "4.8",
                        isFavorite = false,
                        id = 3
                    ),
                    PlaceModel(
                        name = "Grand Canyon",
                        location = "Arizona, USA",
                        imageUrl = "",
                        rating = "4.9",
                        isFavorite = false,
                        id = 4
                    ),
                    PlaceModel(
                        name = "Mısır Çarşısı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "3.6",
                        isFavorite = true,
                        id = 5
                    ),
                ),
                wannaLookPlaces = listOf(
                    PlaceModel(
                        name = "Bryce Canyon National Park",
                        location = "Beyoğlu, İstanbul",
                        imageUrl = "",
                        rating = "4.7",
                        isFavorite = false,
                        id = 1
                    ),
                    PlaceModel(
                        name = "Maiden’s Tower",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "",
                        rating = "4.6",
                        isFavorite = true,
                        id = 2
                    ),
                    PlaceModel(
                        name = "Yerebatan Sarnıcı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "4.8",
                        isFavorite = false,
                        id = 3
                    ),
                    PlaceModel(
                        name = "Grand Canyon",
                        location = "Arizona, USA",
                        imageUrl = "",
                        rating = "4.9",
                        isFavorite = false,
                        id = 4
                    ),
                    PlaceModel(
                        name = "Mısır Çarşısı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "3.6",
                        isFavorite = true,
                        id = 5
                    ),
                )
            ),
        )
}