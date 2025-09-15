package com.beratbaran.loopa.ui.register

object RegisterContract {

    enum class PasswordStrength { STRONG, MEDIUM, WEAK }

    data class UiState(
        val name: String = "",
        val surname: String = "",
        val email: String = "",
        val password: String = "",
        val isNameValid: Boolean = true,
        val isSurnameValid: Boolean = true,
        val isEmailValid: Boolean = true,
        val isPasswordValid: Boolean = true,
        val supportingTextName: String? = null,
        val supportingTextSurname: String? = null,
        val supportingTextEmail: String? = null,
        val supportingTextPassword: String? = null,
        val isLoading: Boolean = false,
        val isRegisterEnabled: Boolean = false,
        val errorMessage: String? = null,
        val submitAttempted: Boolean = false,
        val showPassword: Boolean = false,
        val passwordStrength: PasswordStrength? = null
    )

    sealed interface UiAction {
        data object OnRegisterClicked : UiAction
        data class OnNameChange(val name: String) : UiAction
        data class OnSurnameChange(val surname: String) : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data object OnToggleShowPassword : UiAction
        data object OnSubmitAttempted : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHomePage : UiEffect
    }
}