package com.onirutla.movflex.movie.ui.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieScreenParameterProvider
import com.onirutla.movflex.movie.ui.component.MovieRow

@Composable
fun MovieHomeScreen(
    modifier: Modifier = Modifier,
    content: List<SeeMore<List<Movie>>>,
    onMovieClick: (Movie) -> Unit = {},
    onImageClick: (String) -> Unit = {},
    onSeeMoreClick: (String) -> Unit = {},
    scrollState: LazyListState = rememberLazyListState()
) {
    Surface(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            state = scrollState,
        ) {
            items(content) { item ->
                MovieRow(
                    movies = item.items,
                    movieRowTitle = item.title,
                    onMovieClick = onMovieClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onSeeMoreClick,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun MovieContentPreview(
    @PreviewParameter(MovieScreenParameterProvider::class) content: List<SeeMore<List<Movie>>>
) {
    MovFlexTheme {
        MovieHomeScreen(content = content)
    }
}
