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
    data class DetailsScreen(val placeId: Int) : Screen

    @Serializable
    data object FavoritesScreen : Screen

    @Serializable
    data object CategoriesScreen : Screen

    @Serializable
    data class CategoryDetailsScreen(val categoryId: Int) : Screen

    @Serializable
    data object ProfileScreen : Screen

    @Serializable
    data object SearchScreen : Screen
}
