package com.beratbaran.loopa.ui.register

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class RegisterScreenPreviewProvider : PreviewParameterProvider<RegisterContract.UiState> {
    override val values: Sequence<RegisterContract.UiState>
        get() = sequenceOf(
            RegisterContract.UiState(
                name = "",
                surname = "",
                email = "",
                password = "",
                isLoading = false,
                errorMessage = "",
                isRegisterEnabled = false,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessage = "",
                isRegisterEnabled = true,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = true,
                errorMessage = "",
                isRegisterEnabled = false,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                errorMessage = "Example Register Error",
                isRegisterEnabled = true,
            ),
        )
}