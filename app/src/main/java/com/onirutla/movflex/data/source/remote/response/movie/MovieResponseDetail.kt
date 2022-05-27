package com.onirutla.movflex.data.source.remote.response.movie

import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.local.entities.ItemType
import com.squareup.moshi.Json

data class MovieResponseDetail(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "original_title")
    val originalTitle: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
)

fun MovieResponseDetail.toEntity(): FavoriteEntity = FavoriteEntity(
    id = id ?: 0,
    backdropPath = backdropPath.orEmpty(),
    title = originalTitle.orEmpty(),
    description = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Movie,
    isFavorite = true,
)