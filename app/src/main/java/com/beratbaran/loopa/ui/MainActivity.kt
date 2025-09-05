package com.beratbaran.loopa.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beratbaran.loopa.ui.onboarding.OnboardingScreen
import com.beratbaran.loopa.ui.onboarding.OnboardingViewModel
import com.beratbaran.loopa.ui.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<OnboardingViewModel>()
                    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
                    OnboardingScreen(
                        uiState = uiState,
                        uiEffect = viewModel.uiEffect,
                        onAction = viewModel::onAction,
                        onNavigateToRegister = { },
                        onNavigateToLogin = { },
                    )
                }
            }
        }
    }
}