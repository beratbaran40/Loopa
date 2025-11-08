package com.beratbaran.loopa.ui.register

import com.beratbaran.loopa.common.util.PasswordStrength

object RegisterContract {

    data class UiState(
        val isLoading: Boolean = false,
        val name: String = "",
        val surname: String = "",
        val email: String = "",
        val password: String = "",
        val supportingTextName: String = "",
        val supportingTextSurname: String = "",
        val supportingTextEmail: String = "",
        val supportingTextPassword: String = "",
        val isRegisterEnabled: Boolean = false,
        val isNameTextFieldFocused: Boolean = false,
        val isSurnameTextFieldFocused: Boolean = false,
        val isEmailTextFieldFocused: Boolean = false,
        val isPasswordTextFieldFocused: Boolean = false,
        val showPassword: Boolean = false,
        val passwordStrength: PasswordStrength? = null,
    )

    sealed interface UiAction {
        data object OnRegisterClick : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurnameChange(val surname: String) : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data class OnNameTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnSurnameTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnEmailTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data class OnPasswordTextFieldFocusChange(val isFocused: Boolean) : UiAction
        data object OnToggleShowPassword : UiAction
        data object OnBackClick : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHomePage : UiEffect
        data object NavigateToBack : UiEffect
        data class ShowToast(val message: String) : UiEffect
    }
}