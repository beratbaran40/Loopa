package com.beratbaran.loopa.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun SearchScreen(
    uiState: SearchContract.UiState,
    uiEffect: Flow<SearchContract.UiEffect>,
    onAction: (SearchContract.UiAction) -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToRandomPlace: () -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            SearchContract.UiEffect.NavigateToDetails -> onNavigateToDetails()
            SearchContract.UiEffect.NavigateToRandomPlace -> onNavigateToRandomPlace()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(R.string.search_screen_page_name),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 36.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            SearchBar(
                modifier = Modifier.weight(1f),
                query = uiState.query,
                onQueryChange = { onAction(SearchContract.UiAction.OnQueryChange(it)) },
                onSearch = { onAction(SearchContract.UiAction.OnQueryChange(uiState.query)) },
                onClear = { onAction(SearchContract.UiAction.ClearQuery) },
                uiState = uiState,
            )

            RandomPlaceButton(
                onClick = { onAction(SearchContract.UiAction.OnRandomPlaceClick("")) },
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(
                R.string.search_screen_search_result_text,
                uiState.searchResultQuantity, uiState.searchResultCountry),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(24.dp))

        uiState.places.forEachIndexed { index, place ->
            SearchDetailItem(
                place = place,
                onFavoriteClick = { onAction(SearchContract.UiAction.ToggleFavorite(place.id.toString())) },
                onDetailsClick = { onAction(SearchContract.UiAction.OnDetailsClick(place.id.toString())) },
            )

            if (index != uiState.places.lastIndex)
                Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun SearchScreenPreview(
    @PreviewParameter(SearchScreenPreviewProvider::class) uiState: SearchContract.UiState,
) {
    LoopaTheme {
        SearchScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {},
            onNavigateToRandomPlace = {},
        )
    }
}