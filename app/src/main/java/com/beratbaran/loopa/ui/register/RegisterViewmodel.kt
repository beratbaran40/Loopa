package com.beratbaran.loopa.ui.register

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.common.validateName
import com.beratbaran.loopa.common.validateSurname
import com.beratbaran.loopa.common.validateEmail
import com.beratbaran.loopa.common.validatePassword
import com.beratbaran.loopa.ui.register.RegisterContract.PasswordStrength
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class RegisterViewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegisterContract.UiState(
        )
    )

    val uiState: StateFlow<RegisterContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<RegisterContract.UiEffect>() }
    val uiEffect: Flow<RegisterContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

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
        password: String
    ): Boolean {
        return name.isNotBlank() && surname.isNotBlank() && email.isNotBlank() && password.isNotBlank()
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnRegisterClick -> {
                val currentState = _uiState.value
                val nameError = currentState.name.validateName()
                val surnameError = currentState.surname.validateSurname()
                val emailError = currentState.email.validateEmail()
                val passwordError = currentState.password.validatePassword()
                if (nameError == null && surnameError == null && emailError == null && passwordError == null) {
                    _uiEffect.trySend(RegisterContract.UiEffect.NavigateToHomePage)
                } else {
                    _uiState.update {
                        it.copy(
                            submitClick = true,
                            isNameValid = false,
                            isSurnameValid = false,
                            isEmailValid = false,
                            isPasswordValid = false,
                            isLoading = false,
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
                        uiAction.name, it.surname, it.email, it.password
                    )
                )
            }

            is UiAction.OnSurnameChange -> _uiState.update {
                it.copy(
                    surname = uiAction.surname,
                    isRegisterEnabled = checkRegisterState(
                        it.name, uiAction.surname, it.email, it.password
                    )
                )
            }

            is UiAction.OnEmailChange -> _uiState.update {
                it.copy(
                    email = uiAction.email,
                    isRegisterEnabled = checkRegisterState(
                        it.name, it.surname, uiAction.email, it.password
                    )
                )
            }

            is UiAction.OnPasswordChange -> _uiState.update {
                val newPassword = uiAction.password
                it.copy(
                    password = newPassword,
                    isPasswordValid = newPassword.validatePassword() == null,
                    isRegisterEnabled = checkRegisterState(it.name, it.surname, it.email, newPassword),
                    passwordStrength = computePasswordStrength(newPassword)
                )
            }

            UiAction.OnToggleShowPassword -> _uiState.update {
                it.copy(showPassword = !it.showPassword)
            }

            is UiAction.OnSubmitClick -> _uiState.update { state ->
                val nameError = state.name.validateName()
                val surnameError = state.surname.validateSurname()
                val emailError = state.email.validateEmail()
                val passwordError = state.password.validatePassword()
                state.copy(
                    submitClick = true,
                    supportingTextName = nameError,
                    supportingTextSurname = surnameError,
                    supportingTextEmail = emailError,
                    supportingTextPassword = passwordError,
                    isEmailValid = (emailError == null)
                )
            }

            UiAction.OnBackClick -> _uiEffect.trySend(RegisterContract.UiEffect.NavigateToBack)
        }
    }
}