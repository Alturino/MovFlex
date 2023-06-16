package com.onirutla.movflex.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieColumn
import com.onirutla.movflex.movie.ui.MovieListParameterProvider
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.ui.TvColumn
import com.onirutla.movflex.tv.ui.TvListParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (movie: Movie) -> Unit = {},
    tvList: List<Tv>,
    onTvClick: (tv: Tv) -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
    selectedTab: Int = 0,
    onTabClick: (index: Int) -> Unit = {},
) {
    Scaffold {
        Column(modifier = modifier.padding(it)) {
            FavoriteTabRow(
                selectedTab = selectedTab,
                onTabClick = onTabClick
            )
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

@Preview
@Composable
fun FavoriteScreenMoviePreview(
    @PreviewParameter(MovieListParameterProvider::class)
    movies: List<Movie>,
) {
    MovFlexTheme {
        FavoriteScreen(movies = movies, tvList = emptyList())
    }
}

@Preview
@Composable
fun FavoriteScreenTvPreview(
    @PreviewParameter(TvListParameterProvider::class)
    tvList: List<Tv>,
) {
    MovFlexTheme {
        FavoriteScreen(movies = emptyList(), tvList = tvList, selectedTab = 1)
    }
}
