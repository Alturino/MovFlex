package com.onirutla.movflex.tv.domain.model

import com.onirutla.movflex.core.data.source.remote.response.NetworkResponse
import com.onirutla.movflex.core.data.source.remote.response.SeasonResponse
import com.onirutla.movflex.core.domain.model.CreatedBy
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.LastEpisodeToAir
import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.SpokenLanguage

data class TvContentDetail(
    val adult: Boolean,
    val backdropPath: String,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String,
    val networks: List<NetworkResponse>,
    val nextEpisodeToAir: Any,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<SeasonResponse>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int,
)
