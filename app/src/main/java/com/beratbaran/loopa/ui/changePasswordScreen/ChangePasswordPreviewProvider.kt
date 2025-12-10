package com.beratbaran.loopa.ui.changePasswordScreen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ChangePasswordPreviewProvider: PreviewParameterProvider<ChangePasswordContract.UiState> {
    override val values: Sequence<ChangePasswordContract.UiState>
        get() = sequenceOf(
            ChangePasswordContract.UiState(
                isLoading = true
            ),
            ChangePasswordContract.UiState(
                password = "123456",
                isLoading = false,
                isSaveEnabled = true,
                areFieldsEmpty = false,
                isInEditMode = true,
            )
        )
}