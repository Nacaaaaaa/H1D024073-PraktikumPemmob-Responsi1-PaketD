package com.pemmob.nalendra.aplikasidigimon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.nalendra.aplikasidigimon.ui.screen.DetailScreen
import com.pemmob.nalendra.aplikasidigimon.ui.screen.HomeScreen

// Konsep: Jetpack Compose Navigation
// Mengatur rute antar Screen (Halaman) di dalam aplikasi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Rute ke Home Screen
        composable("home") {
            HomeScreen(
                onNavigateToDetail = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        // Rute ke Detail Screen dengan membawa argumen ID
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(
                digimonId = id,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}