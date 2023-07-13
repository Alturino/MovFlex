package com.onirutla.movflex

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object MovieDetailScreen : Screen("movie")
    object MovieSeeMore : Screen("movie/see_more")
    object FavoriteScreen : Screen("favorite")
}
