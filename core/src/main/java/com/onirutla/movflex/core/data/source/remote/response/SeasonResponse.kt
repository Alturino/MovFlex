package com.onirutla.movflex.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonResponse(
    @Json(name = "air_date")
    val airDate: String? = "",
    @Json(name = "episodes")
    val episodes: List<EpisodeResponse>? = emptyList(),
    @Json(name = "_id")
    val _id: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "season_number")
    val seasonNumber: Int? = 0,
)

