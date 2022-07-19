package com.onirutla.movflex.data.source.remote.response.tv

import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.ItemType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvResponseDetail(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
)

fun TvResponseDetail.toEntity(): FavoriteEntity = FavoriteEntity(
    id = id ?: 0,
    backdropPath = backdropPath.orEmpty(),
    title = name.orEmpty(),
    description = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    type = ItemType.Tv,
    isFavorite = false
)