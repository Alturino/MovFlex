package com.onirutla.movflex.data.source.remote.response.tv

import com.squareup.moshi.Json

data class TvResponseDetail(
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
)