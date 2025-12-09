package com.beratbaran.loopa.ui.profile

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.profile.ProfileContract.UiEffect
import com.beratbaran.loopa.ui.profile.ProfileContract.UiState
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(uiState.isInEditMode) {
        if (uiState.isInEditMode) {
            passwordFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            UiEffect.NavigateToOnboarding -> onNavigateToOnboarding()
            UiEffect.ShowPasswordDoneToast -> Toast.makeText(
                context, context.getString(R.string.profile_screen_toast_message_text),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ProfileHeader(
        )
        ProfileAvatar(
            uiState = uiState
        )

        Spacer(modifier = Modifier.height(4.dp))

        ProfileTextFields(
            onAction = onAction,
            uiState = uiState,
            passwordFocusRequester = passwordFocusRequester,
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileBottomButtons(
            onAction = onAction,
            isInEditMode = uiState.isInEditMode,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }

    if (uiState.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { onAction(UiAction.OnLogoutDismissClick) },
            title = { Text(text = stringResource(id = R.string.profile_screen_logout_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.profile_screen_logout_dialog_text)) },
            confirmButton = {
                TextButton(onClick = { onAction(UiAction.OnLogoutConfirmClick) }) {
                    Text(text = stringResource(id = R.string.profile_screen_logout_dialog_confirm_button))
                }
            },
            dismissButton = {
                TextButton(onClick = { onAction(UiAction.OnLogoutDismissClick) }) {
                    Text(text = stringResource(id = R.string.profile_screen_logout_dialog_dismiss_button))
                }
            }
        )
    }
    if (uiState.isLoading) LoadingBar()
}

@PreviewLightDark
@Composable
fun ProfileScreenPreview(
    @PreviewParameter(ProfileScreenPreviewProvider::class) uiState: UiState,
) {
    LoopaTheme {
        ProfileScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToOnboarding = {},
        )
    }
}