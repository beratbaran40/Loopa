package com.beratbaran.loopa.ui.login

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        LoginContract.UiState(
            isLoading = false,
            errorMessage = "",
            email = "",
            password = "",
            showPassword = false,
            submitAttempted = false,
            isEmailValid = true,
        )
    )
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be blank"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email address"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be blank"
            password.length < 8 -> "Password must be at least 8 characters long"
            else -> null
        }
    }

    private fun canLogin(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnLoginClicked -> {
                val current = _uiState.value
                val emailError = validateEmail(current.email)
                val passwordError = validatePassword(current.password)
                if (emailError == null && passwordError == null) {
                    _uiEffect.trySend(UiEffect.NavigateToHomePage)
                } else {
                    _uiState.update {
                        it.copy(
                            submitAttempted = true,
                            supportingTextEmail = emailError,
                            supportingTextPassword = passwordError,
                            isEmailValid = false,
                            isPasswordValid = false,
                            isLoading = false
                        )
                    }
                }
            }

            is UiAction.OnEmailChange -> _uiState.update {
                it.copy(
                    email = uiAction.email,
                    isLoginEnabled = canLogin(uiAction.email, it.password)
                )
            }

            is UiAction.OnPasswordChange -> _uiState.update {
                it.copy(
                    password = uiAction.password,
                    isLoginEnabled = canLogin(it.email, uiAction.password)
                )
            }

            is UiAction.OnSubmitAttempted -> _uiState.update { state ->
                val emailError = validateEmail(state.email)
                val passwordError = validatePassword(state.password)
                state.copy(
                    submitAttempted = true,
                    supportingTextEmail = emailError,
                    supportingTextPassword = passwordError,
                    isEmailValid = (emailError == null)
                )
            }

            UiAction.OnToggleShowPassword -> _uiState.update {
                it.copy(showPassword = !it.showPassword)
            }
        }
    }
}
