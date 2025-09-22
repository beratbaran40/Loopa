package com.beratbaran.loopa.ui.homepage

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class HomepageScreenPreviewProvider : PreviewParameterProvider<HomepageContract.UiState> {
    override val values: Sequence<HomepageContract.UiState>
        get() = sequenceOf(
            HomepageContract.UiState(
                isLoading = false,
            ),
            HomepageContract.UiState(
                isLoading = true,
            ),
        )
}