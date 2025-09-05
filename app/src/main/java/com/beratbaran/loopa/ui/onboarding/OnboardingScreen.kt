package com.beratbaran.loopa.ui.onboarding

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beratbaran.loopa.R
import com.beratbaran.loopa.common.collectWithLifecycle
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiAction
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiEffect
import com.beratbaran.loopa.ui.onboarding.OnboardingContract.UiState
import com.beratbaran.loopa.ui.theme.MyappTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun OnboardingScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    uiEffect.collectWithLifecycle { uiEffect ->
        when (uiEffect) {
            UiEffect.NavigateToLogin -> onNavigateToLogin()
            UiEffect.NavigateToRegister -> onNavigateToRegister()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        val bgImages = listOf(
            R.drawable.eiffel_tower_onboarding_img1,
            R.drawable.antarctica_onboarding_img2,
            R.drawable.maidens_onboarding_img3,
            R.drawable.serengeti_onboarding_img4
        )

        Crossfade(targetState = uiState.bgIndex, label = "onboarding-bg") { idx ->
            Image(
                painter = painterResource(id = bgImages[idx]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
        )

        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            style = MaterialTheme.typography.displaySmall,
            color = Color.White
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Dots(selected = uiState.bgIndex)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.onboarding_salute_message),
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.onboarding_description),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onAction(UiAction.OnRegisterClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = (Color.Black)
                ),
            ) {
                Text(
                    text = stringResource(R.string.onboarding_button_text),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.onboarding_login_text),
                modifier = Modifier.clickable { onAction(UiAction.OnLoginClick) },
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}

@Composable
private fun Dots(selected: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) { index ->
            val isSelected = index == selected

            Spacer(modifier = Modifier.width(4.dp))

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(if (isSelected) 18.dp else 8.dp)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray.copy(
                            alpha = 0.5f
                        ),
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun OnboardingScreenPreview() {
    MyappTheme {
        OnboardingScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateToRegister = {},
            onNavigateToLogin = {}
        )
    }
}