package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.Season
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

fun SeasonResponse.toSeason(): Season = Season(
    airDate = airDate.orEmpty(),
    episodes = episodes?.toEpisodes().orEmpty(),
    _id = _id.orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    seasonNumber = seasonNumber ?: 0,
)

fun List<SeasonResponse>.toSeasons(): List<Season> = this.map { it.toSeason() }
