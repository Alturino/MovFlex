package com.onirutla.movflex.core.domain.model

import com.onirutla.movflex.core.domain.model.shared.CreatedBy
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.shared.LastEpisodeToAir
import com.onirutla.movflex.core.domain.model.shared.Network
import com.onirutla.movflex.core.domain.model.shared.ProductionCompany
import com.onirutla.movflex.core.domain.model.shared.ProductionCountry
import com.onirutla.movflex.core.domain.model.shared.Season
import com.onirutla.movflex.core.domain.model.shared.SpokenLanguage

interface ContentDetail {
    val adult: Boolean
    val backdropPath: String
    val createdBy: List<CreatedBy>
    val episodeRunTime: List<Int>
    val firstAirDate: String
    val genres: List<Genre>
    val homepage: String
    val id: Int
    val inProduction: Boolean
    val languages: List<String>
    val lastAirDate: String
    val lastEpisodeToAir: LastEpisodeToAir
    val name: String
    val networks: List<Network>
    val nextEpisodeToAir: Any
    val numberOfEpisodes: Int
    val numberOfSeasons: Int
    val originCountry: List<String>
    val originalLanguage: String
    val originalName: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val productionCompanies: List<ProductionCompany>
    val productionCountries: List<ProductionCountry>
    val seasons: List<Season>
    val spokenLanguages: List<SpokenLanguage>
    val status: String
    val tagline: String
    val type: String
    val voteAverage: Double
    val voteCount: Int
}
