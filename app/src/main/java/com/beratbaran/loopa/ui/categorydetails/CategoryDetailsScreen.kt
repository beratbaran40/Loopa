package com.beratbaran.loopa.ui.categorydetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CategoryDetailsScreen(
    uiState: CategoryDetailsContract.UiState,
    uiEffect: Flow<CategoryDetailsContract.UiEffect>,
    onAction: (CategoryDetailsContract.UiAction) -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            CategoryDetailsContract.UiEffect.NavigateToDetails -> onNavigateToDetails()
            CategoryDetailsContract.UiEffect.NavigateToBack -> onNavigateToBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
            .padding(top = 24.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp),
                onClick = { onNavigateToBack() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }

            Text(
                text = stringResource(R.string.category_details_screen_category_name),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp),
            text = stringResource(
                R.string.category_details_screen_place_number_of_place,
                uiState.numberOfPlaces
            ),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(24.dp))

        uiState.places.forEachIndexed { index, place ->
            DetailItem(
                place = place,
                onFavoriteClick = { onAction(CategoryDetailsContract.UiAction.OnToggleFavorite) },
                onDetailsClick = { onAction(CategoryDetailsContract.UiAction.OnDetailsClick) },
            )

            if (index != uiState.places.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun CategoryDetailsScreenPreview(
    @PreviewParameter(CategoryDetailsScreenPreviewProvider::class) uiState: CategoryDetailsContract.UiState,
) {
    LoopaTheme {
        CategoryDetailsScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {},
            onNavigateToBack = {},
        )
    }
}
