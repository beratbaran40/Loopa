package com.beratbaran.loopa.ui.login

object LoginContract {
    data class UiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String?,
        val emailEdited: Boolean = false,
        val submitAttempted: Boolean = false,
        val showPassword: Boolean = false,
        val isEmailValid: Boolean = true,
        val isPasswordValid: Boolean = true,
        val supportingTextEmail: String? = null,
        val supportingTextPassword: String? = null,
        val isLoginEnabled: Boolean = false
    )

    sealed interface UiAction {
        data object OnLoginClicked : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data object OnToggleShowPassword : UiAction
        data object OnSubmitAttempted : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHomePage : UiEffect

    }
}