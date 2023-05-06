package com.onirutla.movflex.tv.core.remote.model

import com.onirutla.movflex.core.data.source.remote.response.CreatedByResponse
import com.onirutla.movflex.core.data.source.remote.response.GenreResponse
import com.onirutla.movflex.core.data.source.remote.response.LastEpisodeToAirResponse
import com.onirutla.movflex.core.data.source.remote.response.NetworkResponse
import com.onirutla.movflex.core.data.source.remote.response.ProductionCompanyResponse
import com.onirutla.movflex.core.data.source.remote.response.ProductionCountryResponse
import com.onirutla.movflex.core.data.source.remote.response.SeasonResponse
import com.onirutla.movflex.core.data.source.remote.response.SpokenLanguageResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailResponse(
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "created_by")
    val createdBy: List<CreatedByResponse>? = emptyList(),
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>? = emptyList(),
    @Json(name = "first_air_date")
    val firstAirDate: String? = "",
    @Json(name = "genres")
    val genres: List<GenreResponse>? = emptyList(),
    @Json(name = "homepage")
    val homepage: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "in_production")
    val inProduction: Boolean? = false,
    @Json(name = "languages")
    val languages: List<String>? = emptyList(),
    @Json(name = "last_air_date")
    val lastAirDate: String? = "",
    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAirResponse? = LastEpisodeToAirResponse(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "networks")
    val networks: List<NetworkResponse>? = emptyList(),
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any?,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int? = 0,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int? = 0,
    @Json(name = "origin_country")
    val originCountry: List<String>? = emptyList(),
    @Json(name = "original_language")
    val originalLanguage: String? = "",
    @Json(name = "original_name")
    val originalName: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "popularity")
    val popularity: Double? = 0.0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyResponse>? = emptyList(),
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryResponse>? = emptyList(),
    @Json(name = "seasons")
    val seasons: List<SeasonResponse>? = emptyList(),
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>? = emptyList(),
    @Json(name = "status")
    val status: String? = "",
    @Json(name = "tagline")
    val tagline: String? = "",
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int? = 0,
)
