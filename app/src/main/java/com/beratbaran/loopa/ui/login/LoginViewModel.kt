package com.beratbaran.loopa.ui.login

import com.beratbaran.loopa.common.util.ValidationManager
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiEffect

class LoginViewModel : BaseViewModel<LoginContract.UiState, UiEffect>(
    initialState = LoginContract.UiState()
) {
    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnLoginClicked -> {
                val currentState = uiState.value
                val emailError = ValidationManager.validateEmail(currentState.email)
                val passwordError = ValidationManager.validatePassword(currentState.password)
                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    setEffect(UiEffect.NavigateToHomePage)
                } else {
                    setState {
                        copy(
                            supportingTextEmail = emailError,
                            supportingTextPassword = passwordError,
                            isLoading = false,
                        )
                    }
                }
            }

            is UiAction.OnEmailChange -> setState {
                copy(
                    email = uiAction.email,
                    isLoginButtonEnabled = checkLoginButtonState(uiAction.email, password),
                )
            }

            is UiAction.OnPasswordChange -> setState {
                copy(
                    password = uiAction.password,
                    isLoginButtonEnabled = checkLoginButtonState(email, uiAction.password),
                )
            }

            is UiAction.OnEmailTextFieldFocusChange -> setState {
                copy(isEmailTextFieldFocused = uiAction.isFocused)
            }

            is UiAction.OnPasswordTextFieldFocusChange -> setState {
                copy(isPasswordTextFieldFocused = uiAction.isFocused)
            }

            UiAction.OnToggleShowPassword -> setState {
                copy(showPassword = !showPassword)
            }

            UiAction.OnBackClick -> setEffect(UiEffect.NavigateToBack)
        }
    }

    private fun checkLoginButtonState(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}