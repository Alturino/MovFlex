package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.LastEpisodeToAir
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LastEpisodeToAirResponse(
    @Json(name = "air_date")
    val airDate: String? = "",
    @Json(name = "episode_number")
    val episodeNumber: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "production_code")
    val productionCode: String? = "",
    @Json(name = "runtime")
    val runtime: Int? = 0,
    @Json(name = "season_number")
    val seasonNumber: Int? = 0,
    @Json(name = "show_id")
    val showId: Int? = 0,
    @Json(name = "still_path")
    val stillPath: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int? = 0,
)

fun LastEpisodeToAirResponse.toLastEpisodeToAir(): LastEpisodeToAir = LastEpisodeToAir(
    airDate = airDate.orEmpty(),
    episodeNumber = episodeNumber ?: 0,
    id = id ?: 0,
    name = name.orEmpty(),
    overview = overview.orEmpty(),
    productionCode = productionCode.orEmpty(),
    runtime = runtime ?: 0,
    seasonNumber = seasonNumber ?: 0,
    showId = showId ?: 0,
    stillPath = stillPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)

fun List<LastEpisodeToAirResponse>.toLastEpisodeToAir(): List<LastEpisodeToAir> =
    this.map { it.toLastEpisodeToAir() }
