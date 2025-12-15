package com.beratbaran.loopa.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.ui.theme.LoopaTheme
import com.beratbaran.loopa.ui.theme.textFieldColors
import kotlinx.coroutines.launch

@Composable
fun LoopaTextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocusChange: ((Boolean) -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent { event ->
                if (event.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
            .onFocusChanged { focusState ->
                onFocusChange?.invoke(focusState.isFocused)
            },
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(12.dp),
        colors = textFieldColors(),
        supportingText = {
            if (!supportingText.isNullOrEmpty()) {
                Text(
                    text = supportingText,
                    color = if (isError) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Composable
fun NameTextFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    isFocused: Boolean = false,
    onFocusChange: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current

    LoopaTextFields(
        value = value,
        modifier = Modifier.padding(bottom = 4.dp),
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_name),
                contentDescription = null,
                tint = when {
                    isError -> MaterialTheme.colorScheme.error
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        },
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                if (imeAction == ImeAction.Next) {
                    onImeAction?.invoke() ?: focusManager.moveFocus(FocusDirection.Down)
                }
            },
            onDone = {
                if (imeAction == ImeAction.Done) {
                    onImeAction?.invoke()
                }
            }
        ),
        onFocusChange = { isFocusedState ->
            onFocusChange?.invoke(isFocusedState)
        }
    )
}

@Composable
fun SurnameTextFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    isFocused: Boolean = false,
    onFocusChange: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current

    LoopaTextFields(
        value = value,
        modifier = Modifier.padding(bottom = 4.dp),
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_name),
                contentDescription = null,
                tint = when {
                    isError -> MaterialTheme.colorScheme.error
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        },
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                if (imeAction == ImeAction.Next) {
                    onImeAction?.invoke() ?: focusManager.moveFocus(FocusDirection.Down)
                }
            },
            onDone = {
                if (imeAction == ImeAction.Done) {
                    onImeAction?.invoke()
                }
            }
        ),
        onFocusChange = { isFocusedState ->
            onFocusChange?.invoke(isFocusedState)
        }
    )
}

@Composable
fun EmailTextFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    isFocused: Boolean = false,
    onFocusChange: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current

    LoopaTextFields(
        modifier = Modifier.padding(bottom = 4.dp),
        value = value,
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = null,
                tint = when {
                    isError -> MaterialTheme.colorScheme.error
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        },
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                if (imeAction == ImeAction.Next) {
                    onImeAction?.invoke() ?: focusManager.moveFocus(FocusDirection.Down)
                }
            },
            onDone = {
                if (imeAction == ImeAction.Done) {
                    onImeAction?.invoke()
                }
            }
        ),
        onFocusChange = { isFocusedState ->
            onFocusChange?.invoke(isFocusedState)
        }
    )
}

@Composable
fun PasswordTextFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    isError: Boolean = false,
    supportingText: String? = null,
    enabled: Boolean = true,
    isFocused: Boolean = false,
    onFocusChange: ((Boolean) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LoopaTextFields(
        modifier = Modifier.padding(bottom = 4.dp),
        value = value,
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password_custom),
                contentDescription = null,
                tint = when {
                    isError -> MaterialTheme.colorScheme.error
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        },
        trailingIcon = {
            IconButton(onClick = { onTogglePasswordVisibility.invoke() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isPasswordVisible) R.drawable.ic_visibility
                        else R.drawable.ic_visibility_off
                    ),
                    contentDescription = stringResource(R.string.login_register_screen_toggle_password),
                )
            }
        },
        isError = isError,
        supportingText = supportingText,
        enabled = enabled,
        singleLine = true,
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (imeAction == ImeAction.Done) {
                    keyboardController?.hide()
                    onImeAction?.invoke()
                }
            }
        ),
        onFocusChange = { isFocusedState ->
            onFocusChange?.invoke(isFocusedState)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    LoopaTheme {
        Column {
            NameTextFieldBase(
                value = "John",
                onValueChange = {},
                label = "Name",
            )

            Spacer(modifier = Modifier.height(4.dp))

            SurnameTextFieldBase(
                value = "Doe",
                onValueChange = {},
                label = "Surname",
            )

            Spacer(modifier = Modifier.height(4.dp))

            EmailTextFieldBase(
                value = "johndoe@hotmail.com",
                onValueChange = {},
                label = "Email",
            )

            Spacer(modifier = Modifier.height(4.dp))

            PasswordTextFieldBase(
                value = "password",
                onValueChange = {},
                label = "Password",
                isPasswordVisible = false,
                onTogglePasswordVisibility = {},
            )
        }
    }
}