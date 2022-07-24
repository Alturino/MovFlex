package com.onirutla.movflex.data.source.remote.response.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
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