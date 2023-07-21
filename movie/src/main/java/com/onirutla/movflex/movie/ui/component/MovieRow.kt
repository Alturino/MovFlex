package com.onirutla.movflex.movie.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.ui.MovieListParameterProvider

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    movieRowTitle: String = "Popular",
    seeMore: String = "See More",
    onSeeMoreClick: (String) -> Unit = {},
    onImageClick: (String) -> Unit = {},
    onMovieClick: (Movie) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = movieRowTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
            )
            Text(
                modifier = Modifier.clickable { onSeeMoreClick(movieRowTitle) },
                text = seeMore,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
            )
        }
        LazyRow {
            items(items = movies) {
                MovieItemRow(
                    movie = it,
                    onImageClick = onImageClick,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}


@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    movieRowTitle: String = "Popular",
    seeMore: String = "See More",
    onSeeMoreClick: () -> Unit,
    onImageClick: (url: String) -> Unit,
    onItemClick: (movie: Movie) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = movieRowTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
            )
            Text(
                modifier = Modifier.clickable { onSeeMoreClick() },
                text = seeMore,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
            )
        }
        LazyRow {
            items(items = movies) {
                if (it != null) {
                    MovieItemRow(
                        movie = it,
                        onImageClick = onImageClick,
                        onMovieClick = onItemClick
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun MovieRowPreview(
    @PreviewParameter(MovieListParameterProvider::class) movies: List<Movie>,
) {
    MovFlexTheme {
        MovieRow(movies = movies)
    }
}
