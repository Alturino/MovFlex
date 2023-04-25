package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.Season
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonResponse(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episodes")
    val episodes: List<EpisodeResponse>,
    @Json(name = "_id")
    val _id: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "season_number")
    val seasonNumber: Int,
)

fun SeasonResponse.toSeason(): Season = Season(
    airDate = airDate,
    episodes = episodes,
    _id = _id,
    id = id,
    name = name,
    overview = overview,
    posterPath = posterPath.orEmpty(),
    seasonNumber = seasonNumber
)

fun List<SeasonResponse>.toSeason(): List<Season> = this.map { it.toSeason() }
