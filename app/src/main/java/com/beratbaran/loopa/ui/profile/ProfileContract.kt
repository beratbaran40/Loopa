package com.beratbaran.loopa.ui.profile

import com.beratbaran.loopa.common.util.PasswordStrength

object ProfileContract {

    data class UiState(
        val isLoading: Boolean = false,
        val imageUrl: String = "",
        val fullName: String = "",
        val name: String = "",
        val surname: String = "",
        val email: String = "",
        val password: String = "",
        val supportingTextName: String = "",
        val supportingTextSurname: String = "",
        val supportingTextEmail: String = "",
        val supportingTextPassword: String = "",
        val isNameTextFieldFocused: Boolean = false,
        val isSurnameTextFieldFocused: Boolean = false,
        val isEmailTextFieldFocused: Boolean = false,
        val isPasswordTextFieldFocused: Boolean = false,
        val showPassword: Boolean = false,
        val passwordStrength: PasswordStrength? = null,
        val isInEditMode: Boolean = false,
        val isSaveEnabled: Boolean = false,
        val areFieldsEmpty: Boolean = true,
        val showLogoutDialog: Boolean = false,
        val showDeleteAccountDialog: Boolean = false,
    )

    sealed interface UiAction {
        data object OnEditPasswordClick : UiAction
        data object OnLogoutClick : UiAction
        data object OnLogoutConfirmClick : UiAction
        data object OnLogoutDismissClick : UiAction
    }

    sealed interface UiEffect {
        data object ShowPasswordDoneToast : UiEffect
        data object NavigateToOnboarding : UiEffect
        data object NavigateToChangePassword : UiEffect
    }
}