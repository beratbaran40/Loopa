package com.beratbaran.loopa.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.beratbaran.loopa.R
import com.beratbaran.loopa.components.EmailTextFieldBase
import com.beratbaran.loopa.components.PasswordTextFieldBase
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.login.LoginContract.UiState

@Composable
fun LoginEmailTextField(
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
fun LoginPasswordTextField(
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
            onAction(UiAction.OnLoginClicked)
        }
    )
}