package com.onirutla.movflex.tv.domain.model

import com.onirutla.movflex.core.domain.model.CreatedBy
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.LastEpisodeToAir
import com.onirutla.movflex.core.domain.model.Network
import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.core.domain.model.SpokenLanguage

data class TvDetail(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val createdBy: List<CreatedBy> = emptyList(),
    val episodeRunTime: List<Int> = emptyList(),
    val firstAirDate: String = "",
    val genres: String = "",
    val homepage: String = "",
    val id: Int = 0,
    val inProduction: Boolean = false,
    val languages: List<String> = emptyList(),
    val lastAirDate: String = "",
    val lastEpisodeToAir: LastEpisodeToAir = LastEpisodeToAir(),
    val name: String = "",
    val networks: List<Network> = emptyList(),
    val nextEpisodeToAir: String = "",
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0,
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
