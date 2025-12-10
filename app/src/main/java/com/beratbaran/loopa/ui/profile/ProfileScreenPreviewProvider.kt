package com.beratbaran.loopa.ui.profile

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beratbaran.loopa.common.util.PasswordStrength

class ProfileScreenPreviewProvider: PreviewParameterProvider<ProfileContract.UiState> {
override val values: Sequence<ProfileContract.UiState>
    get() = sequenceOf(
        ProfileContract.UiState(
            isLoading = true
        ),
        ProfileContract.UiState(
            fullName = "Berat Baran",
            name = "Berat",
            surname = "Baran",
            email = "berat.baran@gmail.com",
            password = "7890",
            isLoading = false,
            isSaveEnabled = false,
            isInEditMode = true
        ),
        ProfileContract.UiState(
            fullName = "John Doe",
            name = "John",
            surname = "Doe",
            email = "test@example.com",
            password = "123456",
            isLoading = false,
            passwordStrength = PasswordStrength.WEAK,
            isSaveEnabled = true
        ),
    )
}
