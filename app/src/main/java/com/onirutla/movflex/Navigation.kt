@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
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
import com.onirutla.movflex.movie.ui.screen.detail.MovieDetailScreen
import com.onirutla.movflex.movie.ui.screen.detail.MovieDetailViewModel
import com.onirutla.movflex.movie.ui.screen.home.MovieHomeScreenViewModel
import com.onirutla.movflex.movie.ui.screen.see_more.MovieSeeMoreScreen
import com.onirutla.movflex.movie.ui.screen.see_more.MovieSeeMoreViewModel
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail
import com.onirutla.movflex.tv.ui.TvDetailScreen
import com.onirutla.movflex.tv.ui.TvSeeMoreScreen
import com.onirutla.movflex.tv.ui.TvViewModel
import com.onirutla.movflex.tv.ui.detail.TvDetailViewModel
import com.onirutla.movflex.tv.ui.more.TvMoreViewModel
import com.onirutla.movflex.core.R as coreR

fun NavGraphBuilder.movieNav(navController: NavHostController): NavGraphBuilder = apply {
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
        val movieId = backStackEntry.arguments?.getInt(MovFlexArg.MovieId.arg) ?: return@composable
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
            onFabClick = { movie -> vm.setFavorite(movie) },
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
        val movieSeeMoreVm: MovieSeeMoreViewModel = hiltViewModel()
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

fun NavGraphBuilder.tvNav(navController: NavHostController): NavGraphBuilder = apply {
    composable(
        route = "${Screen.TvDetailScreen.route}/{${MovFlexArg.TvId.arg}}",
        arguments = listOf(
            navArgument(name = MovFlexArg.TvId.arg) {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) { backStackEntry ->
        val vm: TvDetailViewModel = hiltViewModel()
        val tvId = backStackEntry.arguments?.getInt(MovFlexArg.TvId.arg) ?: return@composable
        vm.getTvDetail(tvId)

        val tvDetail by vm.tvDetail.observeAsState(initial = TvDetail())
        val isTvFavorited by vm.isFavorite.observeAsState(initial = false)
        val reviews by vm.tvReviews.observeAsState(initial = listOf())
        val casts by vm.tvCasts.observeAsState(initial = listOf())
        val tvSimilar by vm.tvSimilar.observeAsState(initial = listOf())
        val tvRecommendations by vm.tvRecommendations.observeAsState(initial = listOf())

        TvDetailScreen(
            tvDetail = tvDetail,
            reviews = reviews,
            onReviewClick = {},
            casts = casts,
            onCastClick = {},
            tvSimilar = tvSimilar,
            tvRecommendations = tvRecommendations,
            onTvClick = { tv -> navController.navigate("${Screen.TvDetailScreen.route}/${tv.id}") },
            onTvSeeMoreClick = {},
            onNavigateUp = { navController.navigateUp() },
            fabState = isTvFavorited,
            onFabClick = { tv -> vm.setFavorite(tv) },
            onImageClick = {}
        )
    }
    composable(
        route = "${Screen.TvSeeMore.route}/{${MovFlexArg.TvSeeMoreTitle.arg}}",
        arguments = listOf(
            navArgument(name = MovFlexArg.TvSeeMoreTitle.arg) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val tvMoreVm: TvMoreViewModel = hiltViewModel()
        val title = backStackEntry.arguments
            ?.getString(MovFlexArg.TvSeeMoreTitle.arg) ?: ""

        val tvId = backStackEntry.arguments
            ?.getInt(MovFlexArg.MovieId.arg) ?: 0

        tvMoreVm.tvId = tvId
        tvMoreVm.getTvByCategory(title)

        val tv: LazyPagingItems<Tv> = tvMoreVm.tvMore.collectAsLazyPagingItems()
        TvSeeMoreScreen(
            title = title,
            tvPaging = tv,
            onNavigateUp = { navController.navigateUp() },
            onImageClick = {},
            onTvClick = { navController.navigate(route = "${Screen.TvDetailScreen.route}/${it.id}") },
        )
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route,
            arguments = listOf()
        ) {
            val movieVm: MovieHomeScreenViewModel = hiltViewModel()
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
                onTvClick = { navController.navigate(route = "${Screen.TvDetailScreen.route}/${it.id}") },
                movieFavoriteContent = favoriteMovieState,
                onMovieSeeMoreClick = { seeMoreTitle -> navController.navigate(route = "${Screen.MovieSeeMore.route}/$seeMoreTitle") },
                onTvSeeMoreClick = { seeMoreTitle -> navController.navigate(route = "${Screen.TvSeeMore.route}/$seeMoreTitle") },
                tvFavoriteContent = favoriteTvState,
                tabItems = tabItems,
                onTabClick = { tabState = it },
                selectedTab = tabState,
            )
        }
        movieNav(navController)
        tvNav(navController)
    }
}
