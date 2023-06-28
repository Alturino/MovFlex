package com.onirutla.movflex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.favorite.FavoriteScreen
import com.onirutla.movflex.favorite.movie.FavoriteMovieViewModel
import com.onirutla.movflex.favorite.tv.FavoriteTvViewModel
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieContent
import com.onirutla.movflex.movie.ui.MovieViewModel
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.ui.TvScreen
import com.onirutla.movflex.tv.ui.TvViewModel
import com.onirutla.movflex.core.R as coreR

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val movieVm: MovieViewModel by hiltViewModel()
            val tvVm: TvViewModel by hiltViewModel()

            val movieState by movieVm.movie.observeAsState(initial = listOf())
            val tvState by tvVm.tvHome.observeAsState(initial = listOf())

            val navigationItems = listOf(
                NavigationItem(
                    title = stringResource(id = coreR.string.movie),
                    icon = Icons.Default.Movie
                ),
                NavigationItem(
                    title = stringResource(id = coreR.string.tv),
                    icon = Icons.Default.Tv
                ),
            )
            var navigationItemState by rememberSaveable { mutableStateOf(0) }

            HomeScreen(
                navigationItems = navigationItems,
                navigationItemSelected = navigationItemState,
                onNavigationItemClick = { navigationItemState = it },
                movieContent = movieState,
                onMovieItemClick = {},
                onImageClick = {},
                tvContent = tvState,
                onTvItemClick = {},
            )
        }

        composable(route = Screen.FavoriteScreen.route) {
            var tabState by rememberSaveable { mutableStateOf(0) }

            val movieVm: FavoriteMovieViewModel by hiltViewModel()
            val tvVm: FavoriteTvViewModel by hiltViewModel()

            val movieState = movieVm.movieFavorite.collectAsLazyPagingItems()
            val tvState = tvVm.tvFavorite.collectAsLazyPagingItems()

            FavoriteScreen(
                moviePaging = movieState,
                onMovieClick = {},
                tvPaging = tvState,
                onTvClick = {},
                selectedTab = tabState,
                onTabClick = { tab: Int -> tabState = tab },
            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationItems: List<NavigationItem>,
    navigationItemSelected: Int = 0,
    onNavigationItemClick: (Int) -> Unit = {},
    movieContent: List<SeeMore<List<Movie>>>,
    onMovieItemClick: (Movie) -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
    onMovieSeeMoreClick: () -> Unit = {},
    tvContent: List<SeeMore<List<Tv>>>,
    onTvItemClick: (Tv) -> Unit = {},
    onTvSeeMoreClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {
            navigationItems.forEachIndexed { index, item ->
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        selected = index == navigationItemSelected,
                        onClick = { onNavigationItemClick(index) },
                        label = { Text(text = item.title) }
                    )
                }
            }
        },
        snackbarHost = {},
        floatingActionButton = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
        ) {
            when (navigationItemSelected) {
                0 -> MovieContent(
                    content = movieContent,
                    onItemClick = onMovieItemClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onMovieSeeMoreClick,
                )

                1 -> TvScreen(
                    content = tvContent,
                    onItemClick = onTvItemClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onTvSeeMoreClick,
                )
            }
        }
    }
}
