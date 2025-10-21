package com.beratbaran.loopa.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.register.toProgress
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.launch

@Composable
fun ProfileTextFields(
    onAction: (UiAction) -> Unit,
    uiState: ProfileContract.UiState,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val canInteract = !uiState.isLoading && uiState.isInEditMode

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(24.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                    onAction(UiAction.OnNameTextFieldFocusChange(event.isFocused))
                },
            value = uiState.name,
            onValueChange = { onAction(UiAction.OnNameChange(it)) },
            label = { Text(text = stringResource(R.string.registerScreen_name_text)) },
            singleLine = true,
            enabled = !uiState.isLoading,
            readOnly = !uiState.isInEditMode,
            isError = uiState.supportingTextName.isNotEmpty(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_name),
                    contentDescription = null,
                    tint = when {
                        uiState.supportingTextName.isNotEmpty() -> MaterialTheme.colorScheme.error
                        uiState.isNameTextFieldFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    if (canInteract) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            supportingText = {
                if (uiState.supportingTextName.isNotEmpty()) {
                    Text(
                        text = uiState.supportingTextName,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                    onAction(UiAction.OnSurnameTextFieldFocusChange(event.isFocused))
                },
            value = uiState.surname,
            onValueChange = { onAction(UiAction.OnSurnameChange(it)) },
            label = { Text(text = stringResource(R.string.registerScreen_surname_text)) },
            singleLine = true,
            enabled = !uiState.isLoading,
            readOnly = !uiState.isInEditMode,
            isError = uiState.supportingTextSurname.isNotEmpty(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_name),
                    contentDescription = null,
                    tint = when {
                        uiState.supportingTextSurname.isNotEmpty() -> MaterialTheme.colorScheme.error
                        uiState.isSurnameTextFieldFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    if (canInteract) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            supportingText = {
                if (uiState.supportingTextSurname.isNotEmpty()) {
                    Text(
                        text = uiState.supportingTextSurname,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch { bringIntoViewRequester.bringIntoView() }
                    }
                    onAction(UiAction.OnEmailTextFieldFocusChange(event.isFocused))
                },
            value = uiState.email,
            onValueChange = { onAction(UiAction.OnEmailChange(it)) },
            label = { Text(text = stringResource(R.string.login_label_mail_text)) },
            singleLine = true,
            enabled = !uiState.isLoading,
            readOnly = !uiState.isInEditMode,
            isError = uiState.supportingTextEmail.isNotEmpty(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    tint = when {
                        uiState.supportingTextEmail.isNotEmpty() -> MaterialTheme.colorScheme.error
                        uiState.isEmailTextFieldFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    if (canInteract) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            supportingText = {
                if (uiState.supportingTextEmail.isNotEmpty()) {
                    Text(
                        text = uiState.supportingTextEmail,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                    onAction(UiAction.OnPasswordTextFieldFocusChange(event.isFocused))
                },
            value = uiState.password,
            onValueChange = { onAction(UiAction.OnPasswordChange(it)) },
            label = { Text(text = stringResource(R.string.login_label_password)) },
            singleLine = true,
            enabled = !uiState.isLoading,
            readOnly = !uiState.isInEditMode,
            isError = uiState.supportingTextPassword.isNotEmpty(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = null,
                    tint = when {
                        uiState.supportingTextPassword.isNotEmpty() -> MaterialTheme.colorScheme.error
                        uiState.isPasswordTextFieldFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            visualTransformation = if (uiState.showPassword) VisualTransformation.None else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { onAction(UiAction.OnToggleShowPassword) },
                    enabled = canInteract
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (uiState.showPassword) R.drawable.ic_visibility
                            else R.drawable.ic_visibility_off
                        ),
                        contentDescription = stringResource(R.string.login_register_screen_toggle_password),
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (canInteract) {
                        keyboardController?.hide()
                        onAction(UiAction.OnConfirmChangesClick)
                    }
                }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            supportingText = {
                if (uiState.supportingTextPassword.isNotEmpty()) {
                    Text(
                        text = uiState.supportingTextPassword,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )
        uiState.passwordStrength?.let { strength ->
            val (progress, color, label) = strength.toProgress()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    progress = { progress },
                    color = color,
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(color = color),
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ProfileTextFieldsPreview() {
    MyappTheme {
        ProfileTextFields(
            onAction = {},
            uiState = ProfileContract.UiState(),
        )
    }
}