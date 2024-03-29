package com.onirutla.movflex.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieListParameterProvider
import com.onirutla.movflex.movie.ui.component.MovieColumn
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.ui.TvColumn
import com.onirutla.movflex.tv.ui.TvListParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (movie: Movie) -> Unit,
    tvList: List<Tv>,
    onTvClick: (tv: Tv) -> Unit,
    onImageClick: (url: String) -> Unit,
    tabItems: List<FavoriteTabItem>,
    selectedTab: Int = 0,
    onTabClick: (index: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            FavoriteTabRow(
                selectedTab = selectedTab,
                tabItems = tabItems,
                onTabClick = onTabClick,
            )
        }
    ) {
        Column(modifier = modifier.padding(it)) {
            when (selectedTab) {
                0 -> {
                    MovieColumn(
                        movies = movies,
                        onImageClick = onImageClick,
                        onMovieClick = onMovieClick,
                    )
                }

                1 -> {
                    TvColumn(
                        tvList = tvList,
                        onImageClick = onImageClick,
                        onItemClick = onTvClick,
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    moviePaging: LazyPagingItems<Movie>,
    onMovieClick: (movie: Movie) -> Unit,
    tvPaging: LazyPagingItems<Tv>,
    onTvClick: (tv: Tv) -> Unit,
    onImageClick: (url: String) -> Unit,
    tabItems: List<FavoriteTabItem>,
    selectedTab: Int = 0,
    onTabClick: (index: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            FavoriteTabRow(
                selectedTab = selectedTab,
                tabItems = tabItems,
                onTabClick = onTabClick
            )
        }
    ) {
        Column(modifier = modifier.padding(it)) {
            when (selectedTab) {
                0 -> {
                    MovieColumn(
                        movies = moviePaging,
                        onImageClick = onImageClick,
                        onMovieClick = onMovieClick,
                    )
                }

                1 -> {
                    TvColumn(
                        tvPaging = tvPaging,
                        onImageClick = onImageClick,
                        onTvClick = onTvClick,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenMoviePreview(
    @PreviewParameter(MovieListParameterProvider::class)
    movies: List<Movie>,
) {
    MovFlexTheme {
        FavoriteContent(
            movies = movies,
            tabItems = listOf(),
            tvList = emptyList(),
            onImageClick = {},
            onTvClick = {},
            onMovieClick = {},
            onTabClick = {}
        )
    }
}

@Preview
@Composable
fun FavoriteScreenTvPreview(
    @PreviewParameter(TvListParameterProvider::class)
    tvList: List<Tv>,
) {
    MovFlexTheme {
        FavoriteContent(
            movies = listOf(),
            tabItems = listOf(),
            tvList = tvList,
            onImageClick = {},
            onTvClick = {},
            onMovieClick = {},
            onTabClick = {}
        )
    }
}
