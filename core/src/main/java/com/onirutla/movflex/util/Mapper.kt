package com.onirutla.movflex.util

import com.onirutla.movflex.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.source.remote.response.tv.TvResponse
import com.onirutla.movflex.model.Content

fun TvResponse.toContent(): Content = Content(
    id = id,
    backdropPath = posterPath.orEmpty(),
    title = name.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    isFavorite = false,
)

fun MovieResponse.toContent(): Content = Content(
    id = id,
    backdropPath = posterPath.orEmpty(),
    title = title.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    isFavorite = false,
)

fun <T, R> List<T>.mapper(mapper: (T) -> R): List<R> {
    return this.map { mapper(it) }
}