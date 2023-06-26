package com.onirutla.movflex.movie.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    content: List<SeeMore<List<Movie>>>,
    onItemClick: (movie: Movie) -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
    onSeeMoreClick: () -> Unit = {},
    scrollState: LazyListState = rememberLazyListState()
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {},
        snackbarHost = {},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(padding),
            state = scrollState,
        ) {
            items(content) { item ->
                MovieRow(
                    movies = item.items,
                    movieRowTitle = item.title,
                    onItemClick = onItemClick,
                    onImageClick = onImageClick,
                    onSeeMoreClick = onSeeMoreClick
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MovieScreenPreview(
    @PreviewParameter(MovieScreenParameterProvider::class) content: List<SeeMore<List<Movie>>>
) {
    MovFlexTheme {
        MovieScreen(content = content)
    }
}
