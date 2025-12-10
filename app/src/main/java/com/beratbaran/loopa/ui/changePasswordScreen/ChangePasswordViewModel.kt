package com.beratbaran.loopa.ui.changePasswordScreen

import com.beratbaran.loopa.common.util.ValidationManager
import com.beratbaran.loopa.domain.repository.UserRepository
import com.beratbaran.loopa.ui.base.BaseViewModel
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiAction
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiEffect
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<UiState, UiAction, UiEffect>(
    initialState = UiState()
) {
    override fun handleAction(action: UiAction) {
        when (action) {
            UiAction.OnEditPasswordClick -> setState {
                copy(
                    isInEditMode = true,
                    isPasswordTextFieldFocused = true,
                    isSaveEnabled = false,
                    areFieldsEmpty = true,
                )
            }

            UiAction.OnCancelChangesClick -> setState {
                val hasSaved = hasSavedInfo()
                val restore = savedProfile
                copy(
                    password = if (hasSaved) restore!!.password else "",
                    supportingTextPassword = "",
                    showPassword = false,
                    isInEditMode = false,
                    passwordStrength = null,
                    isPasswordTextFieldFocused = false,
                    isSaveEnabled = false,
                    areFieldsEmpty = !hasSaved,
                )
            }

            UiAction.OnConfirmChangesClick -> {
                val currentState = currentUiState
                val passwordError = ValidationManager.validatePassword(currentState.password)
                val isSuccess = passwordError.isBlank() && currentState.password.isNotBlank()

                setState {
                    copy(
                        supportingTextPassword = passwordError,
                        isInEditMode = if (isSuccess) false else isInEditMode,
                        isPasswordTextFieldFocused = if (isSuccess) false else isPasswordTextFieldFocused,
                        isSaveEnabled = if (isSuccess) false else isSaveEnabled,
                        areFieldsEmpty = if (isSuccess) true else areFieldsEmpty,
                        passwordStrength = null
                    )
                }

                if (isSuccess) {
                    savedProfile =
                        ProfileSnapShot(
                            password = currentState.password
                        )
                }
                if (isSuccess) {
                    setEffect(UiEffect.ShowPasswordDoneToast)
                }
            }

            is UiAction.OnPasswordChange -> setState {
                val newPassword = action.password
                val newAreFieldsEmpty = newPassword.isBlank()
                copy(
                    password = newPassword,
                    areFieldsEmpty = newAreFieldsEmpty,
                    isSaveEnabled = isConfirmChangesEnabled(
                        password = newPassword,
                    ),
                    passwordStrength = ValidationManager.computePasswordStrength(newPassword),
                )
            }

            is UiAction.OnPasswordTextFieldFocusChange -> setState {
                copy(isPasswordTextFieldFocused = action.isFocused)
            }

            UiAction.OnToggleShowPassword -> setState {
                copy(showPassword = !showPassword)
            }
        }

    }

    private data class ProfileSnapShot(
        val password: String,
    )

    private var savedProfile: ProfileSnapShot? = null

    private fun hasSavedInfo(): Boolean {
        val s = savedProfile
        return s != null && s.password.isNotBlank()
    }

    private fun isConfirmChangesEnabled(
        password: String = currentUiState.password,
    ): Boolean {
        val empty = password.isBlank()
        if (empty) return false

        val passwordErr = ValidationManager.validatePassword(password)
        return passwordErr.isBlank()
    }
}
