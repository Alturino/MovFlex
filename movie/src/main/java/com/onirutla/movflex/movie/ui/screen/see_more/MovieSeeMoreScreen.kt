package com.onirutla.movflex.movie.ui.screen.see_more

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieListParameterProvider
import com.onirutla.movflex.movie.ui.component.MovieColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSeeMoreScreen(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateUp: () -> Unit,
    movies: List<Movie>
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        MovieColumn(modifier = Modifier.padding(padding), movies = movies)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSeeMoreScreen(
    modifier: Modifier = Modifier,
    title: String,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    onNavigateUp: () -> Unit,
    moviePaging: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
    onImageClick: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->
        MovieColumn(
            modifier = Modifier.padding(padding),
            movies = moviePaging,
            onMovieClick = onMovieClick,
            onImageClick = onImageClick
        )
    }
}

@Preview
@Composable
fun MovieSeeMoreScreenPreview(
    @PreviewParameter(MovieListParameterProvider::class) movies: List<Movie>,
) {
    MovFlexTheme {
        MovieSeeMoreScreen(title = "Popular", movies = movies, onNavigateUp = {})
    }
}
