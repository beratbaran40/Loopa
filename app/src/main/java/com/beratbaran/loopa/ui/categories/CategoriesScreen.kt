package com.beratbaran.loopa.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiAction
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiAction.OnCategoryClick
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiEffect
import com.beratbaran.loopa.ui.categories.CategoriesContract.UiState
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CategoriesScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToCategoryDetails: (Int) -> Unit,
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.NavigateToCategoryDetails -> {
                onNavigateToCategoryDetails(effect.categoryId)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.categories_screen_page_name),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(uiState.categories) { item ->
                Box {
                    CategoryItem(
                        category = item,
                        onCategoryClick = { onAction(OnCategoryClick(item.categoryId)) },
                    )
                }
            }
        }
    }
    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun CategoriesScreenPreview(
    @PreviewParameter(CategoriesScreenPreviewProvider::class) uiState: UiState,
) {
    LoopaTheme {
        CategoriesScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToCategoryDetails = {},
        )
    }
}