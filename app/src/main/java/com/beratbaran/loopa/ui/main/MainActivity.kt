package com.beratbaran.loopa.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.beratbaran.loopa.navigation.AppDestination
import com.beratbaran.loopa.navigation.NavGraph
import com.beratbaran.loopa.navigation.Screen
import com.beratbaran.loopa.ui.theme.LoopaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val items = AppDestination.bottomBarItems
            LoopaTheme {
                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        val homeRoute = Screen.Homepage::class.qualifiedName
                        val categoriesRoute = Screen.CategoriesScreen::class.qualifiedName
                        val favoritesRoute = Screen.FavoritesScreen::class.qualifiedName
                        val showBottomBar = currentDestination?.hierarchy?.any { dest ->
                            val r = dest.route?.substringBefore('?')
                            r == homeRoute || r == categoriesRoute || r == favoritesRoute
                        } == true
                        if (showBottomBar) {
                            NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
                                items.forEach { screen ->
                                    val selected = when (screen.route) {
                                        "home" -> currentDestination?.hierarchy?.any {
                                            it.route?.substringBefore(
                                                '?'
                                            ) == homeRoute
                                        } == true

                                        "categories" -> currentDestination?.hierarchy?.any {
                                            it.route?.substringBefore(
                                                '?'
                                            ) == categoriesRoute
                                        }

                                        "favorites" -> currentDestination?.hierarchy?.any {
                                            it.route?.substringBefore(
                                                '?'
                                            ) == favoritesRoute
                                        } == true

                                        else -> false
                                    }

                                    NavigationBarItem(
                                        selected = selected == true,
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            indicatorColor = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.10f
                                            ),
                                        ),
                                        onClick = {
                                            if (selected == true) return@NavigationBarItem
                                            when (screen.route) {
                                                "home" -> navController.navigate(Screen.Homepage) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }

                                                "categories" -> navController.navigate(Screen.CategoriesScreen) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }

                                                "favorites" -> navController.navigate(Screen.FavoritesScreen) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }


                                            }
                                        },
                                        icon = {
                                            val iconId =
                                                if (selected == true) screen.selectedIcon else screen.icon
                                            Icon(
                                                painter = painterResource(id = iconId),
                                                contentDescription = stringResource(id = screen.title)
                                            )
                                        },
                                        label = { Text(text = stringResource(id = screen.title)) },
                                        alwaysShowLabel = false,
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .navigationBarsPadding()
                            .imePadding(),
                        navController = navController,
                        startDestination = Screen.Onboarding
                    )
                }
            }
        }
    }
}