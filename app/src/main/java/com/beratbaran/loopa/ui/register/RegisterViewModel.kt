package com.beratbaran.loopa.ui.register

import com.beratbaran.loopa.common.ValidationManager
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.register.RegisterContract.PasswordStrength
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction

class RegisterViewModel : BaseViewModel<RegisterContract.UiState, RegisterContract.UiEffect>(
    initialState = RegisterContract.UiState()
) {
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
                val currentState = uiState.value
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
                    setState {
                        copy(
                            supportingTextName = nameError,
                            supportingTextSurname = surnameError,
                            supportingTextEmail = emailError,
                            supportingTextPassword = passwordError
                        )
                    }
                }
            }

            is UiAction.OnNameChange -> setState {
                copy(
                    name = uiAction.name,
                    isRegisterEnabled = checkRegisterState(
                        uiAction.name, surname, email, password,
                    )
                )
            }

            is UiAction.OnSurnameChange -> setState {
                copy(
                    surname = uiAction.surname,
                    isRegisterEnabled = checkRegisterState(
                        name, uiAction.surname, email, password,
                    )
                )
            }

            is UiAction.OnEmailChange -> setState {
                copy(
                    email = uiAction.email,
                    isRegisterEnabled = checkRegisterState(
                        name, surname, uiAction.email, password,
                    )
                )
            }

            is UiAction.OnPasswordChange -> setState {
                val newPassword = uiAction.password
                copy(
                    password = newPassword,
                    isRegisterEnabled = checkRegisterState(
                        name = name,
                        surname = surname,
                        email = email,
                        password = newPassword,
                    ),
                    passwordStrength = computePasswordStrength(newPassword),
                )
            }

            is UiAction.OnNameTextFieldFocusChange -> setState {
                copy(isNameTextFieldFocused = uiAction.isFocused)
            }

            is UiAction.OnSurnameTextFieldFocusChange -> setState {
                copy(isSurnameTextFieldFocused = uiAction.isFocused)
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

            UiAction.OnBackClick -> setEffect(RegisterContract.UiEffect.NavigateToBack)
        }
    }
}