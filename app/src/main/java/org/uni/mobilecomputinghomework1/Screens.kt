package org.uni.mobilecomputinghomework1

sealed class Screens(val route: String) {
    data object Home : Screens("HomeScreen")
    data object Food : Screens("FoodScreen/{id}") {
        fun createRoute(id: Int): String {
            return "FoodScreen/$id"
        }
    }

    data object AddFood : Screens("AddFood")
}