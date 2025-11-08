package com.beratbaran.loopa.navigation

import com.beratbaran.loopa.R

sealed class AppDestination(val title: Int, val route: String, val icon: Int, val selectedIcon: Int) {
    data object Home : AppDestination(
        title = R.string.navbar_home_screen_page_name,
        route = Screen.Homepage::class.qualifiedName!!,
        icon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_selected_home
    )

    data object Categories : AppDestination(
        title = R.string.navbar_categories_screen_page_name,
        route = Screen.CategoriesScreen::class.qualifiedName!!,
        icon = R.drawable.ic_category,
        selectedIcon = R.drawable.ic_selected_category
    )

    data object Search : AppDestination(
        title = R.string.navbar_search_screen_page_name,
        route = Screen.SearchScreen::class.qualifiedName!!,
        icon = R.drawable.ic_search,
        selectedIcon = R.drawable.ic_selected_search
    )

    data object Favorites : AppDestination(
        title = R.string.navbar_favorites_screen_page_name,
        route = Screen.FavoritesScreen::class.qualifiedName!!,
        icon = R.drawable.ic_favorite,
        selectedIcon = R.drawable.ic_selected_favorite
    )

    data object Profile : AppDestination(
        title = R.string.navbar_profile_screen_page_name,
        route = Screen.ProfileScreen::class.qualifiedName!!,
        icon = R.drawable.ic_profile,
        selectedIcon = R.drawable.ic_selected_profile
    )
    companion object {
        val bottomBarItems = listOf(Home, Categories, Search, Favorites, Profile)
    }
}