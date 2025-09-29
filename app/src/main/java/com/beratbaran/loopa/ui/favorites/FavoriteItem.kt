package com.beratbaran.loopa.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.beratbaran.loopa.ui.theme.MyappTheme

@Composable
fun FavoriteItem(
    name: String,
    location: String,
    imageUrl: String,
    rating: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit,
) {

}

@PreviewLightDark
@Composable
fun FavoriteItemPreview() {
    MyappTheme {
        FavoriteItem(
            name = "Eiffel Tower",
            location = "Paris, France",
            imageUrl = "",
            rating = "4.8",
            isFavorite = true,
            onFavoriteClick = {},
            onDetailsClick = {},
        )
    }
}