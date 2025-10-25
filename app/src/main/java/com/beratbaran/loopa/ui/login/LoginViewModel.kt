package com.beratbaran.loopa.ui.login

import com.beratbaran.loopa.common.ValidationManager
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : BaseViewModel<UiEffect>() {
    private val _uiState = MutableStateFlow(LoginContract.UiState())
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnLoginClicked -> {
                val currentState = _uiState.value
                val emailError = ValidationManager.validateEmail(currentState.email)
                val passwordError = ValidationManager.validatePassword(currentState.password)
                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    setEffect(UiEffect.NavigateToHomePage)
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

            is UiAction.OnEmailTextFieldFocusChange -> _uiState.update {
                it.copy(isEmailTextFieldFocused = uiAction.isFocused)
            }

            is UiAction.OnPasswordTextFieldFocusChange -> _uiState.update {
                it.copy(isPasswordTextFieldFocused = uiAction.isFocused)
            }

            UiAction.OnToggleShowPassword -> _uiState.update {
                it.copy(showPassword = !it.showPassword)
            }

            UiAction.OnBackClick -> setEffect(UiEffect.NavigateToBack)
        }
    }

    private fun checkLoginButtonState(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}