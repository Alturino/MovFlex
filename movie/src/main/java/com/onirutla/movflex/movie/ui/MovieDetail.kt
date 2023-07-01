package com.onirutla.movflex.movie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.core.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: Movie,
    reviews: List<Review>,
) {
    Scaffold(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE_PATH${movie.posterPath}")
                    .build(),
                contentDescription = "Movie Poster",
                placeholder = painterResource(id = coreR.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Text(text = movie.title)
            Text(text = movie.originalTitle)
            Row {
                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                Text(text = "${movie.voteAverage / 2}")
                Text(text = "(${movie.voteCount})")
                Text(text = movie.genres)
            }
            Text(text = stringResource(id = coreR.string.overview))
            Text(text = movie.overview)
            Text(text = stringResource(id = coreR.string.reviews))
            LazyRow {
                items(reviews) { review ->
                    val imageUrl =
                        if (review.authorDetail.avatarPath.contains("http", ignoreCase = true)) {
                            review.authorDetail.avatarPath.removePrefix("/")
                        } else {
                            review.authorDetail.avatarPath
                        }
                    Card(modifier = Modifier) {
                        AsyncImage(
                            modifier = Modifier
                                .width(250.dp)
                                .height(250.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("$BASE_IMAGE_PATH$imageUrl")
                                .build(),
                            contentDescription = "Reviewer Avatar",
                            placeholder = painterResource(id = coreR.drawable.ic_launcher_background),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovFlexTheme {
        MovieDetailScreen()
    }
}
