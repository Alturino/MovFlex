package com.onirutla.movflex.movie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.ui.MovFlexTheme
import com.onirutla.movflex.core.ui.cast.CastRow
import com.onirutla.movflex.core.ui.review.ReviewRow
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.core.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    reviews: List<Review>,
    casts: List<Cast>,
    movieRecommendations: List<Movie>,
    movieSimilar: List<Movie>,
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
                    .data("$BASE_IMAGE_PATH${movieDetail.posterPath}")
                    .build(),
                contentDescription = "Movie Poster",
                placeholder = painterResource(id = coreR.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            )
            Text(text = movieDetail.title)
            Text(text = movieDetail.originalTitle)
            Row {
                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                Text(text = "${movieDetail.voteAverage / 2}")
                Text(text = "(${movieDetail.voteCount})")
            }
            Text(text = movieDetail.genre)
            Text(text = stringResource(id = coreR.string.overview))
            Text(text = movieDetail.overview)
            Text(text = stringResource(id = coreR.string.reviews))
            ReviewRow(reviews = reviews)
            Text(text = stringResource(id = coreR.string.casts))
            CastRow(casts = casts)
            Text(text = stringResource(id = coreR.string.recommendation))
            MovieRow(movies = movieRecommendations)
            Text(text = stringResource(id = coreR.string.similar))
            MovieRow(movies = movieSimilar)
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovFlexTheme {
//        MovieDetailScreen()
    }
}
