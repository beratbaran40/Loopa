package com.beratbaran.loopa.ui.login

object LoginContract {
    data class UiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String = "",
        val showPassword: Boolean = false,
        val supportingTextEmail: String = "",
        val supportingTextPassword: String = "",
        val isLoginButtonEnabled: Boolean = false,
    )

    sealed interface UiAction {
        data object OnLoginClicked : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data object OnToggleShowPassword : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHomePage : UiEffect
    }
}