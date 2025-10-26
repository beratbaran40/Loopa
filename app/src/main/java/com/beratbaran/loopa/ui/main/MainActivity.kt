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
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.beratbaran.loopa.navigation.AppDestination
import com.beratbaran.loopa.navigation.LoopaBottomBar
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
                        LoopaBottomBar(
                            navController = navController,
                            items = items
                        )
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