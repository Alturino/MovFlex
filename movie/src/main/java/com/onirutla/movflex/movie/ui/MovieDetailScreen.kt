package com.onirutla.movflex.movie.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.domain.model.SpokenLanguage
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
    onReviewClick: (Review) -> Unit,
    casts: List<Cast>,
    onCastClick: (Cast) -> Unit,
    fabState: Boolean = false,
    onFabClick: (MovieDetail) -> Unit,
    movieRecommendations: List<Movie>,
    movieSimilar: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    onMovieSeeMoreClick: () -> Unit,
    onNavigateUp: () -> Unit,
    verticalScrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (fabState) {
                FloatingActionButton(onClick = { onFabClick(movieDetail) }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        tint = Color.Red,
                        contentDescription = "Favorite Button"
                    )
                }
            } else {
                FloatingActionButton(onClick = { onFabClick(movieDetail) }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        tint = Color.Red,
                        contentDescription = "Favorite Button"
                    )
                }
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(contentPadding)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$BASE_IMAGE_PATH${movieDetail.posterPath}")
                        .build(),
                    contentDescription = "Movie Poster",
                    placeholder = painterResource(id = coreR.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop,
                )
                IconButton(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { onNavigateUp() }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,

                    )
                Text(text = movieDetail.originalTitle)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color.Yellow
                    )
                    Text(text = "${movieDetail.voteAverage / 2}")
                    Text(text = "(${movieDetail.voteCount})")
                }
                Text(
                    text = movieDetail.genres,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = stringResource(id = coreR.string.overview),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = movieDetail.overview,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                ReviewRow(
                    title = stringResource(id = coreR.string.reviews),
                    reviews = reviews,
                    onReviewClick = onReviewClick,
                )
                CastRow(
                    title = stringResource(id = coreR.string.casts),
                    casts = casts,
                    onCastClick = onCastClick,
                    seeMore = stringResource(id = coreR.string.see_more)
                )
                MovieRow(
                    title = stringResource(id = coreR.string.recommendation),
                    movies = movieRecommendations,
                    onMovieClick = onMovieClick,
                    onSeeMoreClick = onMovieSeeMoreClick,
                )
                MovieRow(
                    title = stringResource(id = coreR.string.similar),
                    movies = movieSimilar,
                    onMovieClick = onMovieClick,
                    onSeeMoreClick = onMovieSeeMoreClick,
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovFlexTheme {
        MovieDetailScreen(
            movieDetail = MovieDetail(
                adult = false,
                backdropPath = "/xBKGJQsAIeweesB79KC89FpBrVr.jpg",
                budget = 25000000,
                genres = "Drama",
                homepage = "http://www.theshawshankredemption.com/",
                id = 1,
                imdbId = "tt0111161",
                originalLanguage = "en",
                originalTitle = "The Shawshank Redemption",
                overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                popularity = 42.33,
                posterPath = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                productionCompanies = listOf(
                    ProductionCompany(
                        id = 1,
                        logoPath = "Castle Rock Entertainment",
                        name = "",
                        originCountry = ""
                    ),
                    ProductionCompany(
                        id = 2,
                        logoPath = "Castle Rock Entertainment",
                        name = "",
                        originCountry = ""
                    ),
                    ProductionCompany(
                        id = 3,
                        logoPath = "Castle Rock Entertainment",
                        name = "",
                        originCountry = ""
                    )
                ),
                productionCountries = listOf(
                    ProductionCountry(iso31661 = "en-us", name = "United States of America"),
                    ProductionCountry(iso31661 = "en-uk", name = "United Kingdom")
                ),
                releaseDate = "1994-09-23",
                revenue = 28341469,
                runtime = 142,
                spokenLanguages = listOf(
                    SpokenLanguage(englishName = "english", iso6391 = "en-us", name = "English")
                ),
                status = "Released",
                tagline = "Fear can hold you prisoner. Hope can set you free.",
                title = "The Shawshank Redemption",
                video = false,
                voteAverage = 8.7,
                voteCount = 19258,
                isFavorite = true
            ),
            reviews = listOf(),
            onReviewClick = {},
            movieRecommendations = listOf(),
            movieSimilar = listOf(),
            onMovieClick = {},
            casts = listOf(),
            onCastClick = {},
            onMovieSeeMoreClick = {},
            onNavigateUp = {},
            onFabClick = {},
        )
    }
}
