package com.onirutla.movflex.core.data.source.remote.response.shared

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "crew")
    val crew: List<CrewResponse>,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "guest_stars")
    val guestStars: List<GuestStarResponse>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "production_code")
    val productionCode: String,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "show_id")
    val showId: Int,
    @Json(name = "still_path")
    val stillPath: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)
