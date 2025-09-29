package com.beratbaran.loopa.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoritesScreen(
    uiState: FavoritesContract.UiState,
    uiEffect: Flow<FavoritesContract.UiEffect>,
    onAction: (FavoritesContract.UiAction) -> Unit,
) {

}

@PreviewLightDark
@Composable
fun FavoritesScreenPreview() {
    MyappTheme {
        FavoritesScreen(
            uiState = FavoritesContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
        )
    }
}