package com.beratbaran.loopa.ui.register

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.CollectWithLifecycle
import com.beratbaran.loopa.common.showToast
import com.beratbaran.loopa.ui.register.RegisterContract.UiAction
import com.beratbaran.loopa.ui.theme.LoopaTheme
import com.beratbaran.loopa.ui.theme.registerScreenButtonColors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun RegisterScreen(
    uiState: RegisterContract.UiState,
    uiEffect: Flow<RegisterContract.UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToHomepage: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scrollState = rememberScrollState()

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            RegisterContract.UiEffect.NavigateToHomePage -> onNavigateToHomepage()
            RegisterContract.UiEffect.NavigateToBack -> onNavigateToBack()
            is RegisterContract.UiEffect.ShowToast -> context.showToast(effect.message)
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
                interactionSource = remember { MutableInteractionSource() }
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

        RegisterNameTextField(
            uiState = uiState,
            onAction = onAction,
        )

        RegisterSurnameTextField(
            uiState = uiState,
            onAction = onAction
        )

        RegisterEmailTextField(
            uiState = uiState,
            onAction = onAction,
        )

        RegisterPasswordTextField(
            uiState = uiState,
            onAction = onAction,
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .fillMaxWidth()
                .height(56.dp),
            onClick = { onAction(UiAction.OnRegisterClick) },
            shape = RoundedCornerShape(28.dp),
            colors = registerScreenButtonColors(),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 3.dp,
                pressedElevation = 6.dp,
            ),
            enabled = uiState.isRegisterEnabled && !uiState.isLoading,
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(22.dp)
                        .background(Color.Transparent),
                    strokeWidth = 2.dp,
                )
            } else {
                Text(
                    text = stringResource(R.string.registerScreen_registerButton_text),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }

}

@PreviewLightDark
@Composable
fun RegisterScreenPreview(
    @PreviewParameter(RegisterScreenPreviewProvider::class) uiState: RegisterContract.UiState,
) {
    LoopaTheme {
        RegisterScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToHomepage = {},
            onNavigateToBack = {}
        )
    }
}