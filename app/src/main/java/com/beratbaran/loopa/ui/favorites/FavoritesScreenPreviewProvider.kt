package com.beratbaran.loopa.ui.favorites

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class FavoritesScreenPreviewProvider : PreviewParameterProvider<FavoritesContract.UiState> {
    override val values: Sequence<FavoritesContract.UiState>
        get() = sequenceOf(
            FavoritesContract.UiState(
                isLoading = true,
            ),
//            FavoritesContract.UiState(
//                favorites = listOf(
//                    PlaceModel(
//                        name = "Eiffel Tower",
//                        location = "France",
//                        imageUrl = "",
//                        rating = "4.8",
//                        isFavorite = true,
//                    ),
//                    FavoritesModel(
//                        name = "Bryce Canyon National Park",
//                        location = "İstanbul",
//                        imageUrl = "",
//                        rating = "4.7",
//                        isFavorite = true,
//                    ),
//                    FavoritesModel(
//                        name = "Maiden’s Tower",
//                        location = "İstanbul",
//                        imageUrl = "",
//                        rating = "4.6",
//                        isFavorite = true,
//                    ),
//                    FavoritesModel(
//                        name = "Yerebatan Sarnıcı",
//                        location = "İstanbul",
//                        imageUrl = "",
//                        rating = "4.1",
//                        isFavorite = true,
//                    ),
//                    FavoritesModel(
//                        name = "Grand Canyon",
//                        location = "USA",
//                        imageUrl = "",
//                        rating = "4.0",
//                        isFavorite = true,
//                    ),
//                    FavoritesModel(
//                        name = "Mısır Çarşısı",
//                        location = "İstanbul",
//                        imageUrl = "",
//                        rating = "3.6",
//                        isFavorite = true,
//                    ),
//                )
//            )
        )
}