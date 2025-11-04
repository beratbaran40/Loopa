package com.beratbaran.loopa.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import com.beratbaran.loopa.ui.details.DetailsContract.UiAction
import com.beratbaran.loopa.ui.details.DetailsContract.UiEffect
import com.beratbaran.loopa.ui.details.DetailsContract.UiState

@Composable
fun DetailsScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: MutableSharedFlow<UiAction>,
    onNavigateToBack: () -> Unit,
    onNavigateToMaps: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            UiEffect.NavigateToMaps -> onNavigateToMaps()
            UiEffect.NavigateToBack -> onNavigateToBack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DetailsHeader(
                name = uiState.placeName,
                rating = uiState.placeRating,
                mapButton = stringResource(R.string.details_screen_show_on_map_text),
                navigateToMaps = { coroutineScope.launch { onAction.emit(UiAction.ShowOnMapClick) } },
                imageUrl = uiState.placeImageUrl,
                isFavorite = uiState.isFavorite,
                onFavoriteClick = { coroutineScope.launch { onAction.emit(UiAction.ToggleFavorite) } },
                onNavigateToBack = { coroutineScope.launch { onAction.emit(UiAction.OnBackClick) } }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                DetailsCard(
                    category = uiState.placeCategory,
                    categoryName = uiState.placeCategoryName,
                    location = uiState.placeLocation,
                    locationName = uiState.placeLocationName,
                )

                Text(
                    text = stringResource(R.string.details_screen_overview_text),
                    style = MaterialTheme.typography.titleSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )

                Text(
                    text = stringResource(R.string.details_screen_example_place_description_text),
                    style = MaterialTheme.typography.labelLarge
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )

                Text(
                    text = stringResource(R.string.details_screen_photos_text),
                    style = MaterialTheme.typography.titleSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )

                DetailsGrid(
                    uiState = uiState,
                    onImageSelected = {
                        coroutineScope.launch {
                            onAction.emit(
                                UiAction.OnImageSelected(
                                    it
                                )
                            )
                        }
                    }
                )
            }
        }
    }

    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun DetailsScreenPreview(
    @PreviewParameter(DetailsScreenPreviewProvider::class) uiState: UiState,
) {
    LoopaTheme {
        DetailsScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = MutableSharedFlow(),
            onNavigateToBack = {},
            onNavigateToMaps = {},
        )
    }
}