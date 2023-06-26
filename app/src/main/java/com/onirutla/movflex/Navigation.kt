package com.onirutla.movflex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieScreen
import com.onirutla.movflex.movie.ui.MovieViewModel
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.ui.TvScreen
import com.onirutla.movflex.tv.ui.TvViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MovieScreen.route) {
        composable(route = Screen.MovieScreen.route) {
            val vm: MovieViewModel = hiltViewModel()
            val seeMore: List<SeeMore<List<Movie>>> by vm.movie.observeAsState(initial = emptyList())

            MovieScreen(
                content = seeMore,
                onImageClick = {},
                onItemClick = {},
                onSeeMoreClick = {}
            )
        }

        composable(route = Screen.TvScreen.route) {
            val vm: TvViewModel = hiltViewModel()
            val seeMore: List<SeeMore<List<Tv>>> by vm.tvHome.observeAsState(initial = emptyList())

            TvScreen(
                content = seeMore,
                onImageClick = {},
                onItemClick = {},
                onSeeMoreClick = {})
        }
    }
}