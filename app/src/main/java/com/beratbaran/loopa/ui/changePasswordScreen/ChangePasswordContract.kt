package com.beratbaran.loopa.ui.changePasswordScreen

import com.beratbaran.loopa.common.util.PasswordStrength

object ChangePasswordContract {

    data class UiState(
        val isLoading: Boolean = false,
        val password: String = "",
        val supportingTextPassword: String = "",
        val isPasswordTextFieldFocused: Boolean = false,
        val showPassword: Boolean = false,
        val passwordStrength: PasswordStrength? = null,
        val isInEditMode: Boolean = false,
        val isSaveEnabled: Boolean = false,
        val areFieldsEmpty: Boolean = true,
    )

    sealed interface UiAction {
        data object OnConfirmChangesClick : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnPasswordTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data object OnToggleShowPassword : UiAction
        data object OnEditPasswordClick : UiAction
        data object OnCancelChangesClick : UiAction
    }

    sealed interface UiEffect {
        data object ShowPasswordDoneToast : UiEffect
        data object NavigateToProfileScreen : UiEffect

    }
}