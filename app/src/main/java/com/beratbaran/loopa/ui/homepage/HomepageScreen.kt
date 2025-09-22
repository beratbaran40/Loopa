package com.beratbaran.loopa.ui.homepage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beratbaran.loopa.ui.homepage.HomepageContract.UiAction
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomepageScreen(
    uiState: HomepageContract.UiState,
    uiEffect: Flow<HomepageContract.UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToDetails: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Homepage Screen",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomepageScreenPreview(
    @PreviewParameter(HomepageScreenPreviewProvider::class) uiState: HomepageContract.UiState
) {
    MyappTheme {
        HomepageScreen(
            uiState = HomepageContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToDetails = {}
        )
    }
}