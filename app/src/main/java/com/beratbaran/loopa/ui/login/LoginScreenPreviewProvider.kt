package com.beratbaran.loopa.ui.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LoginScreenPreviewProvider : PreviewParameterProvider<LoginContract.UiState> {
    override val values: Sequence<LoginContract.UiState>
        get() = sequenceOf(
            LoginContract.UiState(
                email = "",
                password = "",
                isLoading = false,
                errorMessage = "",
            ),
            LoginContract.UiState(
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessage = "",
                isLoginButtonEnabled = true,
            ),
            LoginContract.UiState(
                email = "test@example",
                password = "123456",
                isLoading = true,
                errorMessage = "",
                supportingTextEmail = "Invalid email format",
                isLoginButtonEnabled = false,
            ),
            LoginContract.UiState(
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessage = "Error",
                supportingTextPassword = "Password must be at least 8 characters",
                isLoginButtonEnabled = false,
            ),
        )
}