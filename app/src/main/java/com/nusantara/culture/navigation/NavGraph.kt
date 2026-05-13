package com.nusantara.culture.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nusantara.culture.ui.screens.AboutScreen
import com.nusantara.culture.ui.screens.DetailScreen
import com.nusantara.culture.ui.screens.HomeScreen
import com.nusantara.culture.viewmodel.CultureViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    object About : Screen("about")
}

@Composable
fun NusantaraNavGraph() {
    val navController = rememberNavController()
    // Single ViewModel shared across all screens so data persists on navigation
    val viewModel: CultureViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetail = { itemId ->
                    navController.navigate(Screen.Detail.createRoute(itemId))
                },
                onNavigateToAbout = {
                    navController.navigate(Screen.About.route)
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable
            DetailScreen(
                itemId = itemId,
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(Screen.About.route) {
            AboutScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
