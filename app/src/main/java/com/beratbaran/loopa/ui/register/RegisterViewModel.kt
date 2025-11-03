package com.beratbaran.loopa.ui.register

import androidx.lifecycle.viewModelScope
import com.beratbaran.loopa.common.util.ValidationManager
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import com.beratbaran.loopa.ui.register.RegisterContract.UiEffect
import com.beratbaran.loopa.ui.register.RegisterContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<UiState, UiEffect>(UiState()) {

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnRegisterClick -> handleRegisterClick()

            is UiAction.OnNameChange -> setState {
                copy(
                    name = uiAction.name,
                    isRegisterEnabled = checkRegisterState(uiAction.name, surname, email, password)
                )
            }

            is UiAction.OnSurnameChange -> setState {
                copy(
                    surname = uiAction.surname,
                    isRegisterEnabled = checkRegisterState(name, uiAction.surname, email, password)
                )
            }

            is UiAction.OnEmailChange -> setState {
                copy(
                    email = uiAction.email,
                    isRegisterEnabled = checkRegisterState(name, surname, uiAction.email, password)
                )
            }

            is UiAction.OnPasswordChange -> setState {
                val newPassword = uiAction.password
                copy(
                    password = newPassword,
                    isRegisterEnabled = checkRegisterState(name, surname, email, newPassword),
                    passwordStrength = ValidationManager.computePasswordStrength(newPassword),
                )
            }

            is UiAction.OnNameTextFieldFocusChange -> setState { copy(isNameTextFieldFocused = uiAction.isFocused) }
            is UiAction.OnEmailTextFieldFocusChange -> setState { copy(isEmailTextFieldFocused = uiAction.isFocused) }
            is UiAction.OnSurnameTextFieldFocusChange -> setState {
                copy(isSurnameTextFieldFocused = uiAction.isFocused)
            }

            is UiAction.OnPasswordTextFieldFocusChange -> setState {
                copy(isPasswordTextFieldFocused = uiAction.isFocused)
            }

            UiAction.OnToggleShowPassword -> setState { copy(showPassword = !showPassword) }
            UiAction.OnBackClick -> setEffect(UiEffect.NavigateToBack)
        }
    }

    private fun handleRegisterClick() {
        val currentState = uiState.value
        val nameError = ValidationManager.validateName(currentState.name)
        val surnameError = ValidationManager.validateSurname(currentState.surname)
        val emailError = ValidationManager.validateEmail(currentState.email)
        val passwordError = ValidationManager.validatePassword(currentState.password)

        val errorList = listOf(nameError, surnameError, emailError, passwordError)
        if (errorList.none(String::isNotEmpty)) {
            register()
        } else {
            setState {
                copy(
                    supportingTextName = nameError,
                    supportingTextSurname = surnameError,
                    supportingTextEmail = emailError,
                    supportingTextPassword = passwordError,
                )
            }
        }
    }

    private fun register() = viewModelScope.launch {
        userRepository.registerUser(
            name = currentUiState.name,
            surname = currentUiState.surname,
            email = currentUiState.email,
            password = currentUiState.password,
        ).fold(
            onSuccess = { setEffect(UiEffect.NavigateToHomePage) },
            onFailure = { setEffect(UiEffect.ShowToast(it.message.orEmpty())) },
        )
    }

    private fun checkRegisterState(
        name: String,
        surname: String,
        email: String,
        password: String,
    ): Boolean {
        return name.isNotBlank() && surname.isNotBlank() && email.isNotBlank() && password.isNotBlank()
    }
}