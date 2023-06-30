package com.onirutla.movflex.movie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.ui.ComponentPreview
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.R
import com.onirutla.movflex.movie.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItemRow(
    modifier: Modifier = Modifier,
    movie: Movie,
    onImageClick: (url: String) -> Unit = {},
    onItemClick: (movie: Movie) -> Unit = {},
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        onClick = { onItemClick(movie) },
    ) {
        AsyncImage(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .clickable { onImageClick("$BASE_IMAGE_PATH${movie.posterPath}") },
            model = ImageRequest.Builder(LocalContext.current)
                .data("$BASE_IMAGE_PATH${movie.posterPath}")
                .build(),
            contentDescription = "Reviewer Avatar",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .width(250.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
            )
            Text(
                text = movie.originalTitle,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
            )
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )

            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Text(
                text = movie.genres,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
                Text(
                    text = "${movie.voteAverage}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
                Text(text = "(${movie.voteCount})")
            }
        }
    }
}

@Composable
@ComponentPreview
private fun MovieItemRowPreview(
    @PreviewParameter(MovieParameterProvider::class) movie: Movie,
) {
    MovFlexTheme {
        MovieItemRow(movie = movie)
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

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    movieRowTitle: String = "Popular",
    seeMore: String = "See More",
    onSeeMoreClick: () -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
    onItemClick: (movie: Movie) -> Unit = {},
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
                MovieItemRow(movie = it, onImageClick = onImageClick, onItemClick = onItemClick)
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
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItemColumn(
    modifier: Modifier = Modifier,
    movie: Movie,
    onImageClick: (url: String) -> Unit,
    onItemClick: (movie: Movie) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        onClick = { onItemClick(movie) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(125.dp)
                    .height(225.dp)
                    .clickable { onImageClick("$BASE_IMAGE_PATH${movie.posterPath}") }
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE_PATH${movie.posterPath}")
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    textAlign = TextAlign.Left,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = "${movie.voteAverage / 2}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = 4,
                        textAlign = TextAlign.Left,
                    )
                }
                Text(
                    text = "Movie",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = movie.genres,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 4,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun MovieItemColumnPreview(
    @PreviewParameter(MovieParameterProvider::class) movie: Movie,
) {
    MovFlexTheme {
        MovieItemColumn(movie = movie, onItemClick = {}, onImageClick = {})
    }
}

@Composable
fun MovieColumn(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (movie: Movie) -> Unit = {},
    onImageClick: (url: String) -> Unit = {},
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
