package com.onirutla.movflex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.favorite.FavoriteContent
import com.onirutla.movflex.favorite.FavoriteTabItem
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieContent
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.ui.TvContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationItems: List<NavigationItem>,
    navigationItemSelected: Int,
    onNavigationItemClick: (Int) -> Unit,
    movieContent: List<SeeMore<List<Movie>>>,
    movieFavoriteContent: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
    onImageClick: (String) -> Unit,
    onMovieSeeMoreClick: () -> Unit,
    tvContent: List<SeeMore<List<Tv>>>,
    tvFavoriteContent: LazyPagingItems<Tv>,
    onTvClick: (Tv) -> Unit,
    onTvSeeMoreClick: () -> Unit,
    tabItems: List<FavoriteTabItem>,
    selectedTab: Int = 0,
    onTabClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        },
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
                    onItemClick = onMovieClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onMovieSeeMoreClick,
                )

                1 -> TvContent(
                    content = tvContent,
                    onItemClick = onTvClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onTvSeeMoreClick,
                )

                2 -> FavoriteContent(
                    moviePaging = movieFavoriteContent,
                    tvPaging = tvFavoriteContent,
                    tabItems = tabItems,
                    selectedTab = selectedTab,
                    onImageClick = onImageClick,
                    onMovieClick = onMovieClick,
                    onTabClick = onTabClick,
                    onTvClick = onTvClick,
                )
            }
        }
    }
}
