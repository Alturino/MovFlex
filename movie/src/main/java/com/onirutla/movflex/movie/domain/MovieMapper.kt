package com.onirutla.movflex.movie.domain

import com.onirutla.movflex.core.data.source.remote.response.shared.toGenre
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.domain.model.MovieContent

fun MovieResponse.toContent(): Content = MovieContent(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genre = Genre(genreIds.first(), ""),
    id = id,
    name = title,
    originCountry = emptyList(),
    originalLanguage = originalLanguage,
    originalName = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = false,
    itemType = ItemType.Movie,
)

fun MovieDetailResponse.toContent(): Content = MovieContent(
    posterPath = posterPath,
    id = id,
    genre = genres.first().toGenre(),
    originalName = originalTitle,
    originalLanguage = originalLanguage,
    originCountry = emptyList(),
    overview = overview,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = false,
    name = title,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    itemType = ItemType.Movie,
)
