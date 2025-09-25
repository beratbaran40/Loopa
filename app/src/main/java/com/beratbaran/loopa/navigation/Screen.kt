package com.beratbaran.loopa.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Homepage : Screen

    @Serializable
    data object DetailsScreen : Screen

    @Serializable
    data object FavoritesScreen : Screen
}