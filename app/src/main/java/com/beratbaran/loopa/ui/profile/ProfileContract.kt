package com.beratbaran.loopa.ui.profile

import com.beratbaran.loopa.common.util.PasswordStrength

object ProfileContract {

    data class UiState(
        val isLoading: Boolean = false,
        val imageUrl: String = "",
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
        val errorMessage: String = "",
        val showPassword: Boolean = false,
        val passwordStrength: PasswordStrength? = null,
        val isInEditMode: Boolean = false,
        val isSaveEnabled: Boolean = false,
        val areFieldsEmpty: Boolean = true,
        val showLogoutDialog: Boolean = false,
        val showDeleteAccountDialog: Boolean = false,
    )

    sealed interface UiAction {
        data object OnConfirmChangesClick : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurnameChange(val surname: String) : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnNameTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnSurnameTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnEmailTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnPasswordTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data object OnToggleShowPassword : UiAction
        data object OnEditProfileClick : UiAction
        data object OnCancelChangesClick : UiAction
        data object OnLogoutClick : UiAction
        data object OnLogoutConfirmClick : UiAction
        data object OnLogoutDismissClick : UiAction
        data object OnDeleteAccountClick : UiAction
        data object OnDeleteAccountConfirmClick : UiAction
        data object OnDeleteAccountDismissClick : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToOnboarding : UiEffect
        data object ShowPasswordDoneToast : UiEffect
    }
}