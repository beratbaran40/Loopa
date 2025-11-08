package com.beratbaran.loopa.ui.register

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beratbaran.loopa.common.util.PasswordStrength


class RegisterScreenPreviewProvider : PreviewParameterProvider<RegisterContract.UiState> {
    override val values: Sequence<RegisterContract.UiState>
        get() = sequenceOf(
            RegisterContract.UiState(
                name = "",
                surname = "",
                email = "",
                password = "",
                isLoading = false,
                isRegisterEnabled = false,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                passwordStrength = PasswordStrength.WEAK,
                isRegisterEnabled = true,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = true,
                isRegisterEnabled = false,
            ),
            RegisterContract.UiState(
                name = "John",
                surname = "Doe",
                email = "test@example.com",
                password = "123456",
                isLoading = false,
                passwordStrength = PasswordStrength.STRONG,
                isRegisterEnabled = true,
            ),
        )
}