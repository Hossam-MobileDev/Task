package com.example.task.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.task.ui.display.DisplayScreen
import com.example.task.ui.input.InputScreen

@Composable
fun UserNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "input"
    ) {
        composable("input") {
            InputScreen(
                onNavigateToDisplay = {
                    navController.navigate("display")
                }
            )
        }
        composable("display") {
            DisplayScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}