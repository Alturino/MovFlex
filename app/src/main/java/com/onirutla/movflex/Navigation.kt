package com.onirutla.movflex

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.onirutla.movflex.favorite.FavoriteTabItem
import com.onirutla.movflex.favorite.movie.FavoriteMovieViewModel
import com.onirutla.movflex.favorite.tv.FavoriteTvViewModel
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.movie.ui.MovieDetailScreen
import com.onirutla.movflex.movie.ui.MovieSeeMoreScreen
import com.onirutla.movflex.movie.ui.MovieViewModel
import com.onirutla.movflex.movie.ui.detail.MovieDetailViewModel
import com.onirutla.movflex.movie.ui.more.MovieMoreViewModel
import com.onirutla.movflex.tv.ui.TvViewModel
import com.onirutla.movflex.core.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val movieVm: MovieViewModel = hiltViewModel()
            val movieState by movieVm.movie.observeAsState(initial = listOf())

            val tvVm: TvViewModel = hiltViewModel()
            val tvState by tvVm.tvHome.observeAsState(initial = listOf())

            val favoriteTvVm: FavoriteTvViewModel = hiltViewModel()
            val favoriteTvState = favoriteTvVm.tvFavorite.collectAsLazyPagingItems()

            val favoriteMovieVm: FavoriteMovieViewModel = hiltViewModel()
            val favoriteMovieState = favoriteMovieVm.movieFavorite.collectAsLazyPagingItems()

            var tabState by rememberSaveable { mutableStateOf(0) }
            val tabItems = listOf(
                FavoriteTabItem(
                    title = stringResource(id = coreR.string.movie),
                    icon = Icons.Default.Movie
                ),
                FavoriteTabItem(
                    title = stringResource(id = coreR.string.tv),
                    icon = Icons.Default.Tv
                )
            )

            var navigationItemState by rememberSaveable { mutableStateOf(0) }
            val bottomNavigationItems = listOf(
                BottomNavigationItem(
                    title = stringResource(id = coreR.string.movie),
                    icon = Icons.Default.Movie
                ),
                BottomNavigationItem(
                    title = stringResource(id = coreR.string.tv),
                    icon = Icons.Default.Tv
                ),
                BottomNavigationItem(
                    title = stringResource(id = coreR.string.favorite),
                    icon = Icons.Default.Favorite
                ),
            )

            HomeScreen(
                bottomNavigationItems = bottomNavigationItems,
                navigationItemSelected = navigationItemState,
                onNavigationItemClick = { navigationItemState = it },
                movieContent = movieState,
                onMovieClick = { navController.navigate(route = "${Screen.MovieDetailScreen.route}/${it.id}") },
                onImageClick = {},
                tvContent = tvState,
                onTvClick = {},
                movieFavoriteContent = favoriteMovieState,
                onMovieSeeMoreClick = { seeMoreTitle -> navController.navigate(route = "${Screen.MovieSeeMore.route}/$seeMoreTitle") },
                onTvSeeMoreClick = {},
                tvFavoriteContent = favoriteTvState,
                tabItems = tabItems,
                onTabClick = { tabState = it },
                selectedTab = tabState,
            )
        }

        composable(
            route = "${Screen.MovieDetailScreen.route}/{${MovFlexArg.MovieId.arg}}",
            arguments = listOf(
                navArgument(name = MovFlexArg.MovieId.arg) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->

            val vm: MovieDetailViewModel = hiltViewModel()
            val movieId = backStackEntry.arguments
                ?.getInt(MovFlexArg.MovieId.arg) ?: return@composable
            vm.getMovieDetail(movieId)

            val movieDetail by vm.movieDetail.observeAsState(initial = MovieDetail())
            val isMovieFavorited by vm.isFavorite.observeAsState(initial = false)
            val reviews by vm.movieReviews.observeAsState(initial = listOf())
            val casts by vm.movieCasts.observeAsState(initial = listOf())
            val movieSimilar by vm.movieSimilar.observeAsState(initial = listOf())
            val movieRecommendations by vm.movieRecommendations.observeAsState(initial = listOf())

            MovieDetailScreen(
                movieDetail = movieDetail,
                reviews = reviews,
                onReviewClick = {},
                casts = casts,
                onCastClick = {},
                movieSimilar = movieSimilar,
                movieRecommendations = movieRecommendations,
                onMovieClick = { movie -> navController.navigate("${Screen.MovieDetailScreen.route}/${movie.id}") },
                onMovieSeeMoreClick = {},
                onNavigateUp = { navController.navigateUp() },
                fabState = isMovieFavorited,
                onFabClick = { movie ->
                    vm.setFavorite(movie)
                },
            )
        }

        composable(
            route = "${Screen.MovieSeeMore.route}/{${MovFlexArg.MovieSeeMoreTitle.arg}}",
            arguments = listOf(
                navArgument(name = MovFlexArg.MovieSeeMoreTitle.arg) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val movieSeeMoreVm: MovieMoreViewModel = hiltViewModel()
            val title = backStackEntry.arguments
                ?.getString(MovFlexArg.MovieSeeMoreTitle.arg) ?: ""

            val movieId = backStackEntry.arguments
                ?.getInt(MovFlexArg.MovieId.arg) ?: 0

            movieSeeMoreVm.movieId = movieId
            movieSeeMoreVm.getMovieByCategory(title)

            val movies: LazyPagingItems<Movie> = movieSeeMoreVm.movieMore.collectAsLazyPagingItems()
            MovieSeeMoreScreen(
                title = title,
                moviePaging = movies,
                onNavigateUp = { navController.navigateUp() },
                onImageClick = {},
                onMovieClick = { movie -> navController.navigate(route = "${Screen.MovieDetailScreen.route}/${movie.id}") },
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String, val icon: ImageVector
)

sealed class MovFlexArg(val arg: String) {
    object MovieId : MovFlexArg(arg = "movie_id")
    object MovieSeeMoreTitle : MovFlexArg(arg = "movie_see_more_title")
}
