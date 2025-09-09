package com.beratbaran.loopa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beratbaran.loopa.ui.onboarding.OnboardingScreen
import com.beratbaran.loopa.ui.onboarding.OnboardingViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Route.Onboarding.path) {

            val viewModel = hiltViewModel<OnboardingViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            OnboardingScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,
                onNavigateToRegister = {
                    navController.navigate(Route.Register.path) {
                        popUpTo(Route.Onboarding.path) {
                            inclusive = false
                        } // inclusive = true olacak ama şimdilik test etme amacıyla false yaptım.
                        launchSingleTop = true
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Route.Login.path) {
                        popUpTo(Route.Onboarding.path) {
                            inclusive = false
                        } // inclusive = true olacak ama şimdilik test etme amacıyla(uygulamayı sürekli yeniden başlatmamak için) false yaptım.
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = Route.Register.path) {

        }

        composable(route = Route.Login.path) {

        }
    }
}