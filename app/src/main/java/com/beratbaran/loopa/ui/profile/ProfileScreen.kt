package com.beratbaran.loopa.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.components.LoadingBar
import com.beratbaran.loopa.ui.profile.ProfileContract.UiEffect
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import com.beratbaran.loopa.ui.profile.ProfileContract.UiAction
import com.beratbaran.loopa.ui.profile.ProfileContract.UiState
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: MutableSharedFlow<UiAction>,
    onNavigateToOnboarding: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileHeader(
            onAction = onAction,
            isInEditMode = uiState.isInEditMode,
            areFieldsEmpty = uiState.areFieldsEmpty,
        )
        ProfileAvatar(
            imageUrl = uiState.imageUrl,
        )
        ProfileTextFields(
            onAction = onAction,
            uiState = uiState,
        )
        ProfileBottomButtons(
            onAction = onAction,
        )
    }

    if (uiState.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { coroutineScope.launch { onAction.emit(UiAction.OnLogoutDismissClick) } },
            title = { Text(text = stringResource(id = R.string.profile_screen_logout_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.profile_screen_logout_dialog_text)) },
            confirmButton = {
                TextButton(onClick = { coroutineScope.launch { onAction.emit(UiAction.OnLogoutConfirmClick) } }) {
                    Text(text = stringResource(id = R.string.profile_screen_logout_dialog_confirm_button))
                }
            },
            dismissButton = {
                TextButton(onClick = { coroutineScope.launch { onAction.emit(UiAction.OnLogoutDismissClick) } }) {
                    Text(text = stringResource(id = R.string.profile_screen_logout_dialog_dismiss_button))
                }
            }
        )
    }
    if (uiState.showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { coroutineScope.launch { onAction.emit(UiAction.OnDeleteAccountDismissClick) } },
            title = { Text(text = stringResource(id = R.string.profile_screen_delete_account_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.profile_screen_delete_account_dialog_text)) },
            confirmButton = {
                TextButton(onClick = { coroutineScope.launch { onAction.emit(UiAction.OnDeleteAccountConfirmClick)  }}) {
                    Text(text = stringResource(id = R.string.profile_screen_delete_account_dialog_confirm_button))
                }
            },
            dismissButton = {
                TextButton(onClick = { coroutineScope.launch { onAction.emit(UiAction.OnDeleteAccountDismissClick) } }) {
                    Text(text = stringResource(id = R.string.profile_screen_delete_account_dialog_dismiss_button))
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
            onAction = MutableSharedFlow(),
            onNavigateToOnboarding = {},
        )
    }
}