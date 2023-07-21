package com.onirutla.movflex.movie.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieListParameterProvider

@Composable
fun MovieColumn(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (movie: Movie) -> Unit,
    onImageClick: (url: String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(movies) {
            if (it != null) {
                MovieItemColumn(
                    movie = it,
                    onImageClick = onImageClick,
                    onItemClick = onMovieClick
                )
            }
        }
    }
}

@Composable
fun MovieColumn(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (movie: Movie) -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(movies) {
            MovieItemColumn(
                movie = it,
                onImageClick = onImageClick,
                onItemClick = onMovieClick
            )
        }
    }
}

@ComponentPreview
@Composable
private fun MovieColumnPreview(
    @PreviewParameter(MovieListParameterProvider::class) movies: List<Movie>,
) {
    MovFlexTheme {
        MovieColumn(movies = movies)
    }
}
