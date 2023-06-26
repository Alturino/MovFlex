package com.onirutla.movflex

sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie")
    object TvScreen : Screen("tv")
    object FavoriteScreen : Screen("favorite")
}
