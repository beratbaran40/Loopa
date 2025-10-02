package com.beratbaran.loopa.ui.favorites

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FavoritesScreenPreviewProvider : PreviewParameterProvider<FavoritesContract.UiState> {
    override val values: Sequence<FavoritesContract.UiState>
        get() = sequenceOf(
            FavoritesContract.UiState(
                isLoading = true
            ),
            FavoritesContract.UiState(
                favoriteItem = listOf(
                    FavoritesModel(
                        name = "Eiffel Tower",
                        location = "Paris, France",
                        imageUrl = "",
                        rating = "4.8",
                        isFavorite = true
                    ),
                    FavoritesModel(
                        name = "Bryce Canyon National Park",
                        location = "Beyoğlu, İstanbul",
                        imageUrl = "",
                        rating = "4.7",
                        isFavorite = true
                    ),
                    FavoritesModel(
                        name = "Maiden’s Tower",
                        location = "Üsküdar, İstanbul",
                        imageUrl = "",
                        rating = "4.6",
                        isFavorite = true
                    ),
                    FavoritesModel(
                        name = "Yerebatan Sarnıcı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "4.1",
                        isFavorite = true
                    ),
                    FavoritesModel(
                        name = "Grand Canyon",
                        location = "Arizona, USA",
                        imageUrl = "",
                        rating = "4.0",
                        isFavorite = true
                    ),
                    FavoritesModel(
                        name = "Mısır Çarşısı",
                        location = "Fatih, İstanbul",
                        imageUrl = "",
                        rating = "3.6",
                        isFavorite = true
                    ),
                )
            )

        )
}