package com.beratbaran.loopa.ui.profile

import com.beratbaran.loopa.common.util.ValidationManager
import com.beratbaran.loopa.data.repository.PlaceRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.profile.ProfileContract.UiEffect
import com.beratbaran.loopa.ui.profile.ProfileContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override suspend fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnEditProfileClick -> setState {
                copy(
                    isInEditMode = true,
                    isNameTextFieldFocused = true,
                    isSurnameTextFieldFocused = false,
                    isEmailTextFieldFocused = false,
                    isPasswordTextFieldFocused = false,
                    isSaveEnabled = false,
                    areFieldsEmpty = true,
                )
            }

            UiAction.OnCancelChangesClick -> setState {
                val hasSaved = hasSavedInfo()
                val restore = savedProfile
                copy(
                    name = if (hasSaved) restore!!.name else "",
                    surname = if (hasSaved) restore!!.surname else "",
                    email = if (hasSaved) restore!!.email else "",
                    password = if (hasSaved) restore!!.password else "",
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
                    areFieldsEmpty = !hasSaved,
                )
            }

            UiAction.OnConfirmChangesClick -> {
                val currentState = currentUiState
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

                setState {
                    copy(
                        supportingTextName = nameError,
                        supportingTextSurname = surnameError,
                        supportingTextEmail = emailError,
                        supportingTextPassword = passwordError,
                        isInEditMode = if (isSuccess) false else isInEditMode,
                        isNameTextFieldFocused = if (isSuccess) false else isNameTextFieldFocused,
                        isSurnameTextFieldFocused = if (isSuccess) false else isSurnameTextFieldFocused,
                        isEmailTextFieldFocused = if (isSuccess) false else isEmailTextFieldFocused,
                        isPasswordTextFieldFocused = if (isSuccess) false else isPasswordTextFieldFocused,
                        isSaveEnabled = if (isSuccess) false else isSaveEnabled,
                        areFieldsEmpty = if (isSuccess) true else areFieldsEmpty,
                        passwordStrength = null
                    )
                }

                if (isSuccess) {
                    savedProfile =
                        ProfileSnapShot(
                            name = currentState.name,
                            surname = currentState.surname,
                            email = currentState.email,
                            password = currentState.password
                        )
                }
                if (isSuccess) {
                    setEffect(UiEffect.ShowPasswordDoneToast)
                }
            }

            is UiAction.OnNameChange -> setState {
                val newName = action.name
                val newAreFieldsEmpty =
                    newName.isBlank() && surname.isBlank() && email.isBlank() && password.isBlank()
                copy(
                    name = newName,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = newName,
                        surname = surname,
                        email = email,
                        password = password,
                    ),
                )
            }

            is UiAction.OnSurnameChange -> setState {
                val newSurname = action.surname
                val newAreFieldsEmpty =
                    name.isBlank() && newSurname.isBlank() && email.isBlank() && password.isBlank()
                copy(
                    surname = newSurname,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = name,
                        surname = newSurname,
                        email = email,
                        password = password,
                    ),
                )
            }

            is UiAction.OnEmailChange -> setState {
                val newEmail = action.email
                val newAreFieldsEmpty =
                    name.isBlank() && surname.isBlank() && newEmail.isBlank() && password.isBlank()
                copy(
                    email = newEmail,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = name,
                        surname = surname,
                        email = newEmail,
                        password = password,
                    ),
                )
            }

            is UiAction.OnPasswordChange -> setState {
                val newPassword = action.password
                val newAreFieldsEmpty =
                    name.isBlank() && surname.isBlank() && email.isBlank() && newPassword.isBlank()
                copy(
                    password = newPassword,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        name = name,
                        surname = surname,
                        email = email,
                        password = newPassword,
                    ),
                    passwordStrength = ValidationManager.computePasswordStrength(newPassword),
                )
            }

            is UiAction.OnNameTextFieldFocusChange -> setState {
                copy(isNameTextFieldFocused = action.isFocused)
            }

            is UiAction.OnSurnameTextFieldFocusChange -> setState {
                copy(isSurnameTextFieldFocused = action.isFocused)
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

            UiAction.OnLogoutClick -> setState { copy(showLogoutDialog = true) }

            UiAction.OnLogoutDismissClick -> setState { copy(showLogoutDialog = false) }

            UiAction.OnLogoutConfirmClick ->
                setEffect(UiEffect.NavigateToOnboarding)

            UiAction.OnDeleteAccountClick ->
                setState { copy(showDeleteAccountDialog = true) }

            UiAction.OnDeleteAccountDismissClick ->
                setState { copy(showDeleteAccountDialog = false) }

            UiAction.OnDeleteAccountConfirmClick -> {
                setEffect(UiEffect.NavigateToOnboarding)
            }
        }
    }

    private data class ProfileSnapShot(
        val name: String,
        val surname: String,
        val email: String,
        val password: String,
    )

    private var savedProfile: ProfileSnapShot? = null

    private fun hasSavedInfo(): Boolean {
        val s = savedProfile
        return s != null &&
                (s.name.isNotBlank() || s.surname.isNotBlank() || s.email.isNotBlank() || s.password.isNotBlank())
    }

    private fun isConfirmChangesEnabled(
        name: String = currentUiState.name,
        surname: String = currentUiState.surname,
        email: String = currentUiState.email,
        password: String = currentUiState.password,
    ): Boolean {
        val allEmpty = name.isBlank() && surname.isBlank() && email.isBlank() && password.isBlank()
        if (allEmpty) return false

        val nameErr = ValidationManager.validateName(name)
        val surnameErr = ValidationManager.validateSurname(surname)
        val emailErr = ValidationManager.validateEmail(email)
        val passwordErr = ValidationManager.validatePassword(password)
        return nameErr.isBlank() && surnameErr.isBlank() && emailErr.isBlank() && passwordErr.isBlank()
    }
}
