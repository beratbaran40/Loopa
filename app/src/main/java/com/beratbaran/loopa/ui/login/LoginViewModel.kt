package com.beratbaran.loopa.ui.login

import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.common.util.ValidationManager
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<LoginContract.UiState, UiAction, UiEffect>(
    initialState = LoginContract.UiState()
) {
    override fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnLoginClicked -> handleLoginClick()

            is UiAction.OnEmailChange -> setState {
                copy(
                    email = action.email,
                    isLoginButtonEnabled = checkLoginButtonState(action.email, password),
                )
            }

            is UiAction.OnPasswordChange -> setState {
                copy(
                    password = action.password,
                    isLoginButtonEnabled = checkLoginButtonState(email, action.password),
                )
            }

            is UiAction.OnEmailTextFieldFocusChange -> setState {
                copy(isEmailTextFieldFocused = action.isFocused)
            }

            is UiAction.OnPasswordTextFieldFocusChange -> setState {
                copy(isPasswordTextFieldFocused = action.isFocused)
            }

            UiAction.OnToggleShowPassword -> setState {
                copy(showPassword = !showPassword)
            }

            UiAction.OnBackClick -> setEffect(UiEffect.NavigateToBack)
        }
    }

    private fun handleLoginClick() {
        val currentState = uiState.value
        val emailError = ValidationManager.validateEmail(currentState.email)
        val passwordError = ValidationManager.validatePassword(currentState.password)
        if (emailError.isEmpty() && passwordError.isEmpty()) {
            login()
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

    private fun login() = viewModelScope.launch {
        setState { copy(isLoading = true) }
        userRepository.loginUser(
            email = uiState.value.email,
            password = uiState.value.password,
        ).fold(
            onSuccess = { setEffect(UiEffect.NavigateToHomePage) },
            onFailure = { setState { copy(isLoading = false) }
                setEffect(UiEffect.ShowToast(it.message.orEmpty()))
            }
        )

    }

    private fun checkLoginButtonState(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}