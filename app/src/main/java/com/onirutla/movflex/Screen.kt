package com.onirutla.movflex

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object MovieDetailScreen : Screen("movie")
    object FavoriteScreen : Screen("favorite")
}
