package com.onirutla.movflex.core.data.source.remote.response.tv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvResponse(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
)
