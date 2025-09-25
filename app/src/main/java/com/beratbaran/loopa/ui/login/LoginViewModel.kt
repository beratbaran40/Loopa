package com.beratbaran.loopa.ui.login

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.common.validateEmail
import com.beratbaran.loopa.common.validatePassword
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
    private val _uiState = MutableStateFlow(LoginContract.UiState())
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnLoginClicked -> {
                val currentState = _uiState.value
                val emailError = currentState.email.validateEmail()
                val passwordError = currentState.password.validatePassword()
                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    _uiEffect.trySend(UiEffect.NavigateToHomePage)
                } else {
                    _uiState.update {
                        it.copy(
                            supportingTextEmail = emailError,
                            supportingTextPassword = passwordError,
                            isLoading = false,
                        )
                    }
                }
            }

            is UiAction.OnEmailChange -> _uiState.update {
                it.copy(
                    email = uiAction.email,
                    isLoginButtonEnabled = checkLoginButtonState(uiAction.email, it.password),
                )
            }

            is UiAction.OnPasswordChange -> _uiState.update {
                it.copy(
                    password = uiAction.password,
                    isLoginButtonEnabled = checkLoginButtonState(it.email, uiAction.password),
                )
            }

            UiAction.OnToggleShowPassword -> _uiState.update {
                it.copy(showPassword = !it.showPassword)
            }
        }
    }

    private fun checkLoginButtonState(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}