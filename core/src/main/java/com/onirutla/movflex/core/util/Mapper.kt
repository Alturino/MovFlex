package com.onirutla.movflex.core.util

import com.onirutla.movflex.core.data.ItemType
import com.onirutla.movflex.core.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.core.data.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.core.data.source.remote.response.movie.MovieResponseDetail
import com.onirutla.movflex.core.data.source.remote.response.tv.TvResponse
import com.onirutla.movflex.core.data.source.remote.response.tv.TvResponseDetail
import com.onirutla.movflex.core.domain.model.Content

fun TvResponse.toContent(): Content = Content(
    id = id,
    backdropPath = posterPath.orEmpty(),
    title = name.orEmpty(),
    description = "",
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Tv,
    isFavorite = false,
)

fun MovieResponse.toContent(): Content = Content(
    id = id,
    backdropPath = posterPath.orEmpty(),
    title = title.orEmpty(),
    description = "",
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Movie,
    isFavorite = false,
)

fun MovieResponseDetail.toContent(): Content = Content(
    id = id ?: 0,
    backdropPath = posterPath.orEmpty(),
    title = originalTitle.orEmpty(),
    description = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Movie,
    isFavorite = false,
)

fun TvResponseDetail.toContent(): Content = Content(
    id = id ?: 0,
    backdropPath = posterPath.orEmpty(),
    title = name.orEmpty(),
    description = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Tv,
    isFavorite = false,
)

fun FavoriteEntity.toContent(): Content = Content(
    id = id,
    backdropPath = backdropPath,
    title = title,
    description = description,
    posterPath = posterPath,
    voteAverage = voteAverage,
    type = type,
    isFavorite = isFavorite,
)

fun Content.toEntity(): FavoriteEntity = FavoriteEntity(
    id = this.id,
    backdropPath = this.backdropPath,
    title = this.title,
    description = description,
    posterPath = posterPath,
    voteAverage = voteAverage,
    type = type,
    isFavorite = isFavorite,
)

inline fun <reified T, R> List<T>.mapper(itemMapper: (T) -> R): List<R> {
    return this.map { itemMapper(it) }
}