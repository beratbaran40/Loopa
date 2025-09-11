package com.beratbaran.loopa.ui.login

object LoginContract {
    data class UiState(
        val bgIndex: Int = 0,
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean,
        val errorMessages: String?,
    )

    sealed interface UiAction {
        data object ClickedToLogin : UiAction
        data class EmailChanged(val email: String) : UiAction
        data class PasswordChanged(val password: String) : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHomePage : UiEffect
    }
}