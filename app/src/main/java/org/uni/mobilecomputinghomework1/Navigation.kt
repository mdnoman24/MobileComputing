package org.uni.mobilecomputinghomework1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import org.uni.mobilecomputinghomework1.addfood.AddFoodScreen
import org.uni.mobilecomputinghomework1.detail.FoodDetailScreen
import org.uni.mobilecomputinghomework1.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.Food.route) {
            val foodId = it.arguments?.getString("id")?.toIntOrNull() ?: 6
            FoodDetailScreen(
                foodId = foodId,
                navController = navController
            )
        }
        composable(
            route = Screens.AddFood.route,
            deepLinks = listOf(navDeepLink { uriPattern = "myapp://addfood" })
        ) {
            AddFoodScreen(navController = navController)
        }
    }
}