package com.beratbaran.loopa.ui.changePasswordScreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiAction
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiEffect
import com.beratbaran.loopa.ui.changePasswordScreen.ChangePasswordContract.UiState
import com.beratbaran.loopa.ui.theme.LoopaTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ChangePasswordScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToProfileScreen: () -> Unit,
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
            UiEffect.NavigateToProfileScreen -> onNavigateToProfileScreen()
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
        verticalArrangement = Arrangement.Center
    )
    {
        ChangePasswordHeader()

        ChangePasswordTextField(
            onAction = onAction,
            uiState = uiState,
            passwordFocusRequester = passwordFocusRequester,
        )

        // Eski şifreyi de gösteren bir textField eklenecek (şimdilik backend şifre döndürmüyor olsa da)

        ChangePasswordButton(
            onAction = onAction,
            isInEditMode = uiState.isInEditMode
        )
    }
}
@PreviewLightDark
@Composable
fun ChangePasswordScreenPreview(
    @PreviewParameter(ChangePasswordPreviewProvider::class) uiState: UiState,
) {
    LoopaTheme {
        ChangePasswordScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToProfileScreen = {},
        )
    }
}


