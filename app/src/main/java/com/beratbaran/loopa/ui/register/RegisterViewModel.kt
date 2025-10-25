package com.beratbaran.loopa.ui.register

import com.beratbaran.loopa.common.ValidationManager
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.register.RegisterContract.PasswordStrength
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : BaseViewModel<RegisterContract.UiEffect>() {
    private val _uiState = MutableStateFlow(
        RegisterContract.UiState(
        )
    )

    val uiState: StateFlow<RegisterContract.UiState> = _uiState.asStateFlow()

    private fun computePasswordStrength(password: String): PasswordStrength? {
        if (password.isBlank()) return null

        var score = 0
        val length = password.length
        val hasLower = password.any { it.isLowerCase() }
        val hasUpper = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }

        score += when {
            length >= 12 -> 2
            length >= 8 -> 1
            else -> 0
        }

        if (hasLower) score++
        if (hasUpper) score++
        if (hasDigit) score++
        if (hasSpecial) score++

        return when {
            score >= 5 -> PasswordStrength.STRONG
            score >= 3 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.WEAK
        }
    }

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
                    setEffect(RegisterContract.UiEffect.NavigateToHomePage)
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
                    passwordStrength = computePasswordStrength(newPassword),
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

            UiAction.OnBackClick -> setEffect(RegisterContract.UiEffect.NavigateToBack)
        }
    }
}