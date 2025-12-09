package com.beratbaran.loopa.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.favorites.FavoritesContract.UiAction
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoritesScreen(
    uiState: FavoritesContract.UiState,
    uiEffect: Flow<FavoritesContract.UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetails: (Int) -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is FavoritesContract.UiEffect.NavigateToDetails -> onNavigateToDetails(effect.placeId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.favorites_screen_page_name),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(uiState.favorites) { place ->
                FavoriteItem(
                    place = place,
                    //item = item,
                    onUnFavoriteClick = { onAction(UiAction.OnUnFavoriteClick) },
                    onDetailsClick = { onAction(UiAction.OnDetailsClick(place.id)) }
                )
            }
        }
    }
    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun FavoritesScreenPreview(
    @PreviewParameter(FavoritesScreenPreviewProvider::class) uiState: FavoritesContract.UiState,
) {
    LoopaTheme {
        FavoritesScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {},
        )
    }
}