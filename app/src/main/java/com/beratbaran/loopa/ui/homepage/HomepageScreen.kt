package com.beratbaran.loopa.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@Composable
fun HomepageScreen(
    uiState: HomepageContract.UiState,
    uiEffect: Flow<HomepageContract.UiEffect>,
    onAction: MutableSharedFlow<UiAction>,
    onNavigateToDetails: () -> Unit,
    onNavigateToFavorites: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            HomepageContract.UiEffect.NavigateToDetails -> onNavigateToDetails()
            HomepageContract.UiEffect.NavigateToFavorites -> onNavigateToFavorites()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(100.dp)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.loopa),
            contentDescription = null,
        )

        Text(
            text = stringResource(R.string.homepage_top_salute_message, uiState.name),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(R.string.homepage_top_places),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            uiState.topPlaces.forEach { place ->
                TopPlaceItem(
                    name = place.name,
                    location = place.location,
                    imageUrl = place.imageUrl,
                    rating = place.rating,
                    isFavorite = place.isFavorite,
                    onFavoriteClick = { coroutineScope.launch { onAction.emit(UiAction.ToggleFavorite) } },
                    onDetailsClick = { coroutineScope.launch { onAction.emit(UiAction.OnDetailsClick) } }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.homepage_you_might_wanna_look),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        uiState.wannaLookPlaces.forEachIndexed { index, place ->
            LookItem(
                name = place.name,
                location = place.location,
                imageUrl = place.imageUrl,
                rating = place.rating,
                isFavorite = place.isFavorite,
                onFavoriteClick = { coroutineScope.launch { onAction.emit(UiAction.ToggleFavorite) } },
                onDetailsClick = { coroutineScope.launch { onAction.emit(UiAction.OnDetailsClick) } },
            )
            if (index != uiState.wannaLookPlaces.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun HomepageScreenPreview(
    @PreviewParameter(HomepageScreenPreviewProvider::class) uiState: HomepageContract.UiState,
) {
    LoopaTheme {
        HomepageScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = MutableSharedFlow(),
            onNavigateToDetails = {},
            onNavigateToFavorites = {},
        )
    }
}