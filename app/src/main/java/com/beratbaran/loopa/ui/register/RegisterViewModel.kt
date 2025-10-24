package com.beratbaran.loopa.ui.register

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.common.ValidationManager
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegisterContract.UiState(
        )
    )

    val uiState: StateFlow<RegisterContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<RegisterContract.UiEffect>() }
    val uiEffect: Flow<RegisterContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    private fun checkRegisterState(
        name: String,
        surname: String,
        email: String,
        password: String,
    ): Boolean {
        return name.isNotBlank() && surname.isNotBlank() && email.isNotBlank() && password.isNotBlank()
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnRegisterClick -> {
                val currentState = _uiState.value
                val nameError = ValidationManager.validateName(currentState.name)
                val surnameError = ValidationManager.validateSurname(currentState.surname)
                val emailError = ValidationManager.validateEmail(currentState.email)
                val passwordError = ValidationManager.validatePassword(currentState.password)

                if (listOf(
                        nameError,
                        surnameError,
                        emailError,
                        passwordError
                    ).none(String::isNotEmpty)
                ) {
                    _uiEffect.trySend(RegisterContract.UiEffect.NavigateToHomePage)
                } else {
                    _uiState.update {
                        it.copy(
                            supportingTextName = nameError,
                            supportingTextSurname = surnameError,
                            supportingTextEmail = emailError,
                            supportingTextPassword = passwordError
                        )
                    }
                }
            }

            is UiAction.OnNameChange -> _uiState.update {
                it.copy(
                    name = uiAction.name,
                    isRegisterEnabled = checkRegisterState(
                        uiAction.name, it.surname, it.email, it.password,
                    )
                )
            }

            is UiAction.OnSurnameChange -> _uiState.update {
                it.copy(
                    surname = uiAction.surname,
                    isRegisterEnabled = checkRegisterState(
                        it.name, uiAction.surname, it.email, it.password,
                    )
                )
            }

            is UiAction.OnEmailChange -> _uiState.update {
                it.copy(
                    email = uiAction.email,
                    isRegisterEnabled = checkRegisterState(
                        it.name, it.surname, uiAction.email, it.password,
                    )
                )
            }

            is UiAction.OnPasswordChange -> _uiState.update {
                val newPassword = uiAction.password
                it.copy(
                    password = newPassword,
                    isRegisterEnabled = checkRegisterState(
                        name = it.name,
                        surname = it.surname,
                        email = it.email,
                        password = newPassword,
                    ),
                    passwordStrength = ValidationManager.computePasswordStrength(newPassword),
                )
            }

            is UiAction.OnNameTextFieldFocusChange -> _uiState.update {
                it.copy(isNameTextFieldFocused = uiAction.isFocused)
            }

            is UiAction.OnSurnameTextFieldFocusChange -> _uiState.update {
                it.copy(isSurnameTextFieldFocused = uiAction.isFocused)
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

            UiAction.OnBackClick -> _uiEffect.trySend(RegisterContract.UiEffect.NavigateToBack)
        }
    }
}