package com.onirutla.movflex.tv.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onirutla.movflex.tv.domain.model.Tv

private val tvList = listOf(
    Tv(
        backdropPath = "backdrop2.jpg",
        firstAirDate = "2022-03-20",
        genres = "Comedy",
        id = 54321,
        name = "Laugh Out Loud",
        originCountry = listOf("US", "UK"),
        originalLanguage = "English",
        originalName = "Laugh Out Loud",
        overview = "A hilarious comedy series that will make you burst into laughter.",
        popularity = 7.9,
        posterPath = "poster2.jpg",
        voteAverage = 7.5,
        voteCount = 800
    ),
    Tv(
        backdropPath = "backdrop2.jpg",
        firstAirDate = "2020-05-10",
        genres = "Action, Crime, Drama",
        id = 98765,
        name = "Crime Files",
        originCountry = listOf("US"),
        originalLanguage = "English",
        originalName = "Crime Files",
        overview = "An intense crime drama that will keep you on the edge of your seat.",
        popularity = 9.1,
        posterPath = "poster3.jpg",
        voteAverage = 9.0,
        voteCount = 1500
    ),
    Tv(
        backdropPath = "backdrop3.jpg",
        firstAirDate = "2023-02-05",
        genres = "Sci-Fi, Mystery",
        id = 24680,
        name = "The Unknown",
        originCountry = listOf("UK"),
        originalLanguage = "English",
        originalName = "The Unknown",
        overview = "A mind-bending sci-fi series with mysterious twists.",
        popularity = 8.7,
        posterPath = "poster4.jpg",
        voteAverage = 8.4,
        voteCount = 1200
    ),
)

class TvParameterProvider(
    override val values: Sequence<Tv> = tvList.asSequence()
) : PreviewParameterProvider<Tv>

class TvListParameterProvider(
    override val values: Sequence<List<Tv>> = sequenceOf(tvList)
) : PreviewParameterProvider<List<Tv>>
