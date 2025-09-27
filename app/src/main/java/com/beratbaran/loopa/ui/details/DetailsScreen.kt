package com.beratbaran.loopa.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun DetailsScreen(
    uiState: DetailsContract.UiState,
    uiEffect: Flow<DetailsContract.UiEffect>,
    onAction: (DetailsContract.UiAction) -> Unit,
    onNavigateToBack: () -> Unit,
    onNavigateToMaps: () -> Unit,
) {

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            DetailsContract.UiEffect.NavigateToMaps -> onNavigateToMaps()
            DetailsContract.UiEffect.NavigateToBack -> onNavigateToBack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            Modifier
                .fillMaxHeight()
        ) {

            DetailsHeader(
                name = uiState.placeName,
                rating = uiState.placeRating,
                mapButton = "Show on Map",
                navigateToMaps = { onAction(DetailsContract.UiAction.ShowOnMapClick) },
                imageUrl = uiState.placeImageUrl,
                isFavorite = uiState.isFavorite,
                onFavoriteClick = { onAction(DetailsContract.UiAction.ToggleFavorite) },
                onNavigateToBack = { onAction(DetailsContract.UiAction.OnBackClick) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {

                DetailsCard(
                    category = uiState.placeCategory,
                    categoryName = uiState.placeCategoryName,
                    location = uiState.placeLocation,
                    locationName = uiState.placeLocationName,
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {

                Text(
                    text = stringResource(R.string.details_screen_overview_text),
                    style = MaterialTheme.typography.titleSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.details_screen_example_place_description_text),
                    style = MaterialTheme.typography.labelLarge
                        .copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.details_screen_photos_text),
                    style = MaterialTheme.typography.titleSmall
                        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )

                Spacer(modifier = Modifier.height(4.dp))

                DetailsGrid()
            }
        }
    }

    if (uiState.isLoading) LoadingBar()
}


@PreviewLightDark
@Composable
fun DetailsScreenPreview(
    @PreviewParameter(DetailsScreenPreviewProvider::class) uiState: DetailsContract.UiState,
) {
    MyappTheme {
        DetailsScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToBack = {},
            onNavigateToMaps = {},
        )
    }
}