package com.beratbaran.loopa.ui.profile

import androidx.lifecycle.ViewModel
import com.beratbaran.loopa.common.ValidationManager
import com.beratbaran.loopa.data.repository.PlaceRepository
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val placeRepository: PlaceRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<ProfileContract.UiEffect>() }
    val uiEffect: Flow<ProfileContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    private fun isConfirmChangesEnabled(
        name: String = _uiState.value.name,
        surname: String = _uiState.value.surname,
        email: String = _uiState.value.email,
        password: String = _uiState.value.password): Boolean {
        val allEmpty = name.isBlank() && surname.isBlank() && email.isBlank() && password.isBlank()
        if (allEmpty) return false

        val nameErr = ValidationManager.validateName(name)
        val surnameErr = ValidationManager.validateSurname(surname)
        val emailErr = ValidationManager.validateEmail(email)
        val passwordErr = ValidationManager.validatePassword(password)
        return nameErr.isBlank() && surnameErr.isBlank() && emailErr.isBlank() && passwordErr.isBlank()
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnEditProfileClick -> _uiState.update { current ->
                current.copy(
                    isInEditMode = true,
                    isNameTextFieldFocused = true,
                    isSurnameTextFieldFocused = false,
                    isEmailTextFieldFocused = false,
                    isPasswordTextFieldFocused = false,
                    isSaveEnabled = false,
                    areFieldsEmpty = true,
                )
            }

            UiAction.OnCancelChangesClick -> _uiState.update { current ->
                current.copy(
                    name = UiAction.OnNameChange(current.name).name,
                    surname = UiAction.OnSurnameChange(current.surname).surname,
                    email = UiAction.OnEmailChange(current.email).email,
                    password = UiAction.OnPasswordChange(current.password).password,
                    supportingTextName = "",
                    supportingTextSurname = "",
                    supportingTextEmail = "",
                    supportingTextPassword = "",
                    showPassword = false,
                    isInEditMode = false,
                    passwordStrength = null,
                    isNameTextFieldFocused = false,
                    isSurnameTextFieldFocused = false,
                    isEmailTextFieldFocused = false,
                    isPasswordTextFieldFocused = false,
                    isSaveEnabled = false,
                    areFieldsEmpty = true,
                )
            }

            UiAction.OnConfirmChangesClick -> {
                val currentState = _uiState.value
                val nameError = ValidationManager.validateName(currentState.name)
                val surnameError = ValidationManager.validateSurname(currentState.surname)
                val emailError = ValidationManager.validateEmail(currentState.email)
                val passwordError = ValidationManager.validatePassword(currentState.password)
                val isSuccess =
                    nameError.isBlank() &&
                            surnameError.isBlank() &&
                            emailError.isBlank() &&
                            passwordError.isBlank() &&
                            currentState.password.isNotBlank()

                _uiState.update {
                    it.copy(
                        supportingTextName = nameError,
                        supportingTextSurname = surnameError,
                        supportingTextEmail = emailError,
                        supportingTextPassword = passwordError,
                        isInEditMode = if (isSuccess) false else it.isInEditMode,
                        isNameTextFieldFocused = if (isSuccess) false else it.isNameTextFieldFocused,
                        isSurnameTextFieldFocused = if (isSuccess) false else it.isSurnameTextFieldFocused,
                        isEmailTextFieldFocused = if (isSuccess) false else it.isEmailTextFieldFocused,
                        isPasswordTextFieldFocused = if (isSuccess) false else it.isPasswordTextFieldFocused,
                        isSaveEnabled = if (isSuccess) false else it.isSaveEnabled,
                        areFieldsEmpty = if (isSuccess) true else it.areFieldsEmpty,
                        passwordStrength = null
                    )
                }

                if (isSuccess) {
                    _uiEffect.trySend(ProfileContract.UiEffect.ShowPasswordDoneToast)
                }
            }

            is UiAction.OnNameChange -> _uiState.update {
                val newName = uiAction.name
                val newAreFieldsEmpty =
                    newName.isBlank() && it.surname.isBlank() && it.email.isBlank() && it.password.isBlank()
                it.copy(
                    name = newName,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = newName,
                        surname = it.surname,
                        email = it.email,
                        password = it.password,
                    ),
                )
            }

            is UiAction.OnSurnameChange -> _uiState.update {
                val newSurname = uiAction.surname
                val newAreFieldsEmpty =
                    it.name.isBlank() && newSurname.isBlank() && it.email.isBlank() && it.password.isBlank()
                it.copy(
                    surname = newSurname,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = it.name,
                        surname = newSurname,
                        email = it.email,
                        password = it.password,
                    ),
                )
            }

            is UiAction.OnEmailChange -> _uiState.update {
                val newEmail = uiAction.email
                val newAreFieldsEmpty =
                    it.name.isBlank() && it.surname.isBlank() && newEmail.isBlank() && it.password.isBlank()
                it.copy(
                    email = newEmail,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = it.name,
                        surname = it.surname,
                        email = newEmail,
                        password = it.password,
                    ),
                )
            }

            is UiAction.OnPasswordChange -> _uiState.update {
                val newPassword = uiAction.password
                val newAreFieldsEmpty =
                    it.name.isBlank() && it.surname.isBlank() && it.email.isBlank() && newPassword.isBlank()
                it.copy(
                    password = newPassword,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
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

            UiAction.OnBackClick -> _uiEffect.trySend(ProfileContract.UiEffect.NavigateToBack)

            UiAction.OnLogoutClick -> _uiState.update { it.copy(showLogoutDialog = true) }

            UiAction.OnLogoutDismissClick -> _uiState.update { it.copy(showLogoutDialog = false) }

            UiAction.OnLogoutConfirmClick ->
                _uiEffect.trySend(ProfileContract.UiEffect.NavigateToOnboarding)

            UiAction.OnDeleteAccountClick ->
                _uiState.update { it.copy(showDeleteAccountDialog = true) }

            UiAction.OnDeleteAccountDismissClick ->
                _uiState.update { it.copy(showDeleteAccountDialog = false) }

            UiAction.OnDeleteAccountConfirmClick -> {
                _uiEffect.trySend(ProfileContract.UiEffect.NavigateToOnboarding)
            }
        }
    }
}
