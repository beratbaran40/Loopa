package com.beratbaran.loopa.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.beratbaran.loopa.R
import com.beratbaran.loopa.components.EmailTextFieldBase
import com.beratbaran.loopa.components.NameTextFieldBase
import com.beratbaran.loopa.components.PasswordTextFieldBase
import com.beratbaran.loopa.components.SurnameTextFieldBase
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import com.beratbaran.loopa.ui.register.RegisterContract.UiState

@Composable
fun RegisterNameTextField(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    NameTextFieldBase(
        value = uiState.name,
        onValueChange = { onAction(UiAction.OnNameChange(it)) },
        label = stringResource(R.string.registerScreen_name_text),
        isError = uiState.supportingTextName.isNotEmpty(),
        supportingText = uiState.supportingTextName.takeIf { it.isNotEmpty() },
        enabled = !uiState.isLoading,
        isFocused = uiState.isNameTextFieldFocused,
        onFocusChange = { isFocused -> onAction(UiAction.OnNameTextFieldFocusChange(isFocused)) }
    )
}

@Composable
fun RegisterSurnameTextField(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    SurnameTextFieldBase(
        value = uiState.surname,
        onValueChange = { onAction(UiAction.OnSurnameChange(it)) },
        label = stringResource(R.string.registerScreen_surname_text),
        isError = uiState.supportingTextSurname.isNotEmpty(),
        supportingText = uiState.supportingTextSurname.takeIf { it.isNotEmpty() },
        enabled = !uiState.isLoading,
        isFocused = uiState.isSurnameTextFieldFocused,
        onFocusChange = { isFocused -> onAction(UiAction.OnSurnameTextFieldFocusChange(isFocused)) }
    )
}

@Composable
fun RegisterEmailTextField(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    EmailTextFieldBase(
        value = uiState.email,
        onValueChange = { onAction(UiAction.OnEmailChange(it)) },
        label = stringResource(R.string.login_label_mail_text),
        isError = uiState.supportingTextEmail.isNotEmpty(),
        supportingText = uiState.supportingTextEmail.takeIf { it.isNotEmpty() },
        enabled = !uiState.isLoading,
        isFocused = uiState.isEmailTextFieldFocused,
        onFocusChange = { isFocused -> onAction(UiAction.OnEmailTextFieldFocusChange(isFocused)) }
    )
}

@Composable
fun RegisterPasswordTextField(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    PasswordTextFieldBase(
        value = uiState.password,
        onValueChange = { onAction(UiAction.OnPasswordChange(it)) },
        label = stringResource(R.string.login_label_password),
        isPasswordVisible = uiState.showPassword,
        onTogglePasswordVisibility = { onAction(UiAction.OnToggleShowPassword) },
        isError = uiState.supportingTextPassword.isNotEmpty(),
        supportingText = uiState.supportingTextPassword.takeIf { it.isNotEmpty() },
        enabled = !uiState.isLoading,
        isFocused = uiState.isPasswordTextFieldFocused,
        onFocusChange = { isFocused ->
            onAction(UiAction.OnPasswordTextFieldFocusChange(isFocused))
        },
        imeAction = ImeAction.Done,
        onImeAction = {
            onAction(UiAction.OnRegisterClick)
        }
    )
}