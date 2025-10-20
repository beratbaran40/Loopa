package com.beratbaran.loopa.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.ui.login.LoginContract.UiAction
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    uiState: LoginContract.UiState,
    uiEffect: Flow<LoginContract.UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToHomepage: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scrollState = rememberScrollState()

    val visualTransformation = if (uiState.showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            LoginContract.UiEffect.NavigateToHomePage -> onNavigateToHomepage()
            LoginContract.UiEffect.NavigateToBack -> onNavigateToBack()
        }
    }

    Column(
        modifier = Modifier

            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
            .imePadding()
            .padding(24.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },

                ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                onClick = { onNavigateToBack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.50f),
                        shape = CircleShape,
                    ),
            ) {

                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                        .clickable { onNavigateToBack() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                )
            )
        }

        Image(
            modifier = Modifier
                .height(240.dp)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.loopa),
            contentDescription = null,
        )

        OutlinedTextField(
            modifier = Modifier
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
                    onAction(
                        UiAction.OnEmailTextFieldFocusChange(
                            focusState.isFocused
                        )
                    )
                },
            value = uiState.email,
            onValueChange = { onAction(UiAction.OnEmailChange(it)) },
            label = { Text(text = stringResource(R.string.login_label_mail_text)) },
            singleLine = true,
            isError = uiState.supportingTextEmail.isNotEmpty(),
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(25.dp),
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
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
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
                    onAction(
                        UiAction.OnPasswordTextFieldFocusChange(
                            focusState.isFocused
                        )
                    )
                },
            value = uiState.password,
            onValueChange = { onAction(UiAction.OnPasswordChange(it)) },
            label = { Text(text = stringResource(R.string.login_label_password)) },
            singleLine = true,
            isError = uiState.supportingTextPassword.isNotEmpty(),
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = null,
                    tint = when {
                        uiState.supportingTextPassword.isNotEmpty() -> MaterialTheme.colorScheme.error
                        uiState.isPasswordTextFieldFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            visualTransformation = visualTransformation,
            trailingIcon = {
                IconButton(onClick = { onAction(UiAction.OnToggleShowPassword) }) {
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
                    keyboardController?.hide()
                    onAction(UiAction.OnLoginClicked)
                }
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
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
                if (uiState.supportingTextPassword.isNotEmpty()) {
                    Text(
                        text = uiState.supportingTextPassword,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .fillMaxWidth()
                .height(56.dp),
            onClick = { onAction(UiAction.OnLoginClicked) },
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                disabledContentColor = MaterialTheme.colorScheme.background,
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 3.dp,
                pressedElevation = 6.dp,
            ),
            enabled = uiState.isLoginButtonEnabled,
        ) {
            Text(
                text = stringResource(R.string.loginScreen_loginButton_text),
                style = MaterialTheme.typography.bodyLarge,
            )
        }

    }
}

@PreviewLightDark
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewProvider::class) uiState: LoginContract.UiState,
) {
    LoopaTheme {
        LoginScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToHomepage = {},
            onNavigateToBack = {},
        )
    }
}