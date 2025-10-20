package com.beratbaran.loopa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beratbaran.loopa.ui.details.DetailsScreen
import com.beratbaran.loopa.ui.details.DetailsViewModel
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsScreen
import com.beratbaran.loopa.ui.categorydetails.CategoryDetailsViewModel
import com.beratbaran.loopa.ui.categories.CategoriesScreen
import com.beratbaran.loopa.ui.categories.CategoriesViewModel
import com.beratbaran.loopa.ui.favorites.FavoritesScreen
import com.beratbaran.loopa.ui.favorites.FavoritesViewModel
import com.beratbaran.loopa.ui.homepage.HomepageScreen
import com.beratbaran.loopa.ui.homepage.HomepageViewModel
import com.beratbaran.loopa.ui.login.LoginScreen
import com.beratbaran.loopa.ui.login.LoginViewModel
import com.beratbaran.loopa.ui.onboarding.OnboardingScreen
import com.beratbaran.loopa.ui.onboarding.OnboardingViewModel
import com.beratbaran.loopa.ui.register.RegisterScreen
import com.beratbaran.loopa.ui.register.RegisterViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Screen.Onboarding> {
            val viewModel = hiltViewModel<OnboardingViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            OnboardingScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register) {
                        popUpTo(Screen.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.Register> {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            RegisterScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,
                onNavigateToHomepage = {
                    navController.navigate(Screen.Homepage) {
                        popUpTo(Screen.Register) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToBack = {
                    navController.navigate(Screen.Onboarding) {
                        popUpTo(Screen.Register) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.Login> {
            val viewModel = hiltViewModel<LoginViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LoginScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,
                onNavigateToHomepage = {
                    navController.navigate(Screen.Homepage) {
                        popUpTo(Screen.Login) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToBack = {
                    navController.navigate(Screen.Onboarding) {
                        popUpTo(Screen.Login) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Screen.Homepage> {
            val viewModel = hiltViewModel<HomepageViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            HomepageScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,

                onNavigateToDetails = {
                    navController.navigate(Screen.DetailsScreen) {
                        popUpTo(Screen.Homepage) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },

                onNavigateToFavorites = {
                    navController.navigate(Screen.FavoritesScreen) {
                        popUpTo(Screen.Homepage) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Screen.DetailsScreen> {
            val viewModel = hiltViewModel<DetailsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            DetailsScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,
                onNavigateToBack = {
                    navController.navigate(Screen.Homepage) {
                        popUpTo(Screen.DetailsScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToMaps = {
                    navController.navigate(Screen.Homepage) {
                        popUpTo(Screen.DetailsScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.FavoritesScreen> {
            val viewModel = hiltViewModel<FavoritesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            FavoritesScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,

                onNavigateToDetails = {
                    navController.navigate(Screen.DetailsScreen) {
                        popUpTo(Screen.FavoritesScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.DetailsScreen> {

        }

        composable<Screen.CategoryDetailsScreen> {
            val viewModel = hiltViewModel<CategoryDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CategoryDetailsScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,

                onNavigateToDetails = {
                    navController.navigate(Screen.DetailsScreen) {
                        popUpTo(Screen.CategoryDetailsScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },

                onNavigateToBack = {
                    navController.navigate(Screen.Homepage) {
                        popUpTo(Screen.CategoryDetailsScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Screen.CategoriesScreen> {
            val viewModel = hiltViewModel<CategoriesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            CategoriesScreen(
                uiState = uiState,
                uiEffect = viewModel.uiEffect,
                onAction = viewModel::onAction,

                onNavigateToCategoryDetails = {
                    navController.navigate(Screen.CategoryDetailsScreen) {
                        popUpTo(Screen.CategoriesScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Screen.CategoryDetailsScreen> {

        }
    }
}