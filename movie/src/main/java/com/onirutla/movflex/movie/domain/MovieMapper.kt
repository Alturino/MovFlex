package com.onirutla.movflex.movie.domain

import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponseDetail
import com.onirutla.movflex.movie.domain.model.MovieContent

fun MovieResponse.toContent(): Content =
    MovieContent(
        id = id,
        backdropPath = posterPath.orEmpty(),
        title = title.orEmpty(),
        description = "",
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        isFavorite = false,
    )

fun MovieResponseDetail.toContent(): Content =
    MovieContent(
        id = id ?: 0,
        backdropPath = posterPath.orEmpty(),
        title = originalTitle.orEmpty(),
        description = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        isFavorite = false,
    )
