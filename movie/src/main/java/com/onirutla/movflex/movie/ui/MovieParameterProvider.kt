package com.onirutla.movflex.movie.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onirutla.movflex.movie.domain.model.Movie


private val movies = listOf(
    Movie(
        id = 1,
        originalTitle = "The Shawshank Redemption",
        releaseDate = "1994-09-23",
        title = "The Shawshank Redemption",
        backdropPath = "/xBKGJQsAIeweesB79KC89FpBrVr.jpg",
        genres = "Drama",
        originalLanguage = "en",
        originalName = "The Shawshank Redemption",
        overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
        popularity = 42.33,
        posterPath = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
        voteAverage = 8.7,
        voteCount = 19258,
        isFavorite = true
    ),
    Movie(
        id = 2,
        originalTitle = "The Godfather",
        releaseDate = "1972-03-14",
        title = "The Godfather",
        backdropPath = "/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg",
        genres = "Crime, Drama",
        originalLanguage = "en",
        originalName = "The Godfather",
        overview = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
        popularity = 39.14,
        posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        voteAverage = 8.7,
        voteCount = 14750,
        isFavorite = false
    ),
)

class MovieParameterProvider(
    override val values: Sequence<Movie> = movies.asSequence()
) : PreviewParameterProvider<Movie>

class MovieListParameterProvider(
    override val values: Sequence<List<Movie>> = sequenceOf(movies)
) : PreviewParameterProvider<List<Movie>>
