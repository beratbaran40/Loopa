package com.beratbaran.loopa.ui.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LoginScreenPreviewProvider : PreviewParameterProvider<LoginContract.UiState> {
    override val values: Sequence<LoginContract.UiState>
        get() = sequenceOf(
            LoginContract.UiState(
                email = "",
                password = "",
                isLoading = false,
                errorMessages = null,
            ),
            LoginContract.UiState(
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessages = null,
            ),
            LoginContract.UiState(
                email = "test@example.com",
                password = "123456",
                isLoading = true,
                errorMessages = null,
            ),
            LoginContract.UiState(
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessages = "Error",
            ),
        )
}