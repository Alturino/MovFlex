package com.onirutla.movflex.movie.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    firstRowMovies: List<Movie>,
    firstRowTitle: String,
    secondRowMovies: List<Movie> = emptyList(),
    secondRowTitle: String = "",
    thirdRowMovies: List<Movie> = emptyList(),
    thirdRowTitle: String = "",
    fourthRowMovies: List<Movie> = emptyList(),
    fourthRowTitle: String = "",
    verticalScrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {},
        snackbarHost = {},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(verticalScrollState)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(padding)
        ) {
            MovieRow(
                movies = firstRowMovies,
                movieRowTitle = firstRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            MovieRow(
                movies = secondRowMovies,
                movieRowTitle = secondRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            MovieRow(
                movies = thirdRowMovies,
                movieRowTitle = thirdRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
            MovieRow(
                movies = fourthRowMovies,
                movieRowTitle = fourthRowTitle,
                onItemClick = {},
                onImageClick = {},
                onSeeMoreClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MovieScreenPreview(
    @PreviewParameter(MovieListParameterProvider::class) movies: List<Movie>
) {
    MovFlexTheme {
        MovieScreen(
            firstRowMovies = movies,
            firstRowTitle = stringResource(id = MovieType.MOVIE_POPULAR.value),
            secondRowMovies = movies,
            secondRowTitle = stringResource(id = MovieType.MOVIE_UPCOMING.value),
            thirdRowMovies = movies,
            thirdRowTitle = stringResource(id = MovieType.MOVIE_TOP_RATED.value),
            fourthRowMovies = movies,
            fourthRowTitle = stringResource(id = MovieType.MOVIE_NOW_PLAYING.value),
        )
    }
}
