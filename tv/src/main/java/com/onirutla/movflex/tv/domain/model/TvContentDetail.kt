package com.onirutla.movflex.tv.domain.model

import com.onirutla.movflex.core.domain.model.ContentDetail
import com.onirutla.movflex.core.domain.model.shared.CreatedBy
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.shared.LastEpisodeToAir
import com.onirutla.movflex.core.domain.model.shared.Network
import com.onirutla.movflex.core.domain.model.shared.ProductionCompany
import com.onirutla.movflex.core.domain.model.shared.ProductionCountry
import com.onirutla.movflex.core.domain.model.shared.Season
import com.onirutla.movflex.core.domain.model.shared.SpokenLanguage

data class TvContentDetail(
    override val adult: Boolean,
    override val backdropPath: String,
    override val createdBy: List<CreatedBy>,
    override val episodeRunTime: List<Int>,
    override val firstAirDate: String,
    override val genres: List<Genre>,
    override val homepage: String,
    override val id: Int,
    override val inProduction: Boolean,
    override val languages: List<String>,
    override val lastAirDate: String,
    override val lastEpisodeToAir: LastEpisodeToAir,
    override val name: String,
    override val networks: List<Network>,
    override val nextEpisodeToAir: Any,
    override val numberOfEpisodes: Int,
    override val numberOfSeasons: Int,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val originalName: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String,
    override val productionCompanies: List<ProductionCompany>,
    override val productionCountries: List<ProductionCountry>,
    override val seasons: List<Season>,
    override val spokenLanguages: List<SpokenLanguage>,
    override val status: String,
    override val tagline: String,
    override val type: String,
    override val voteAverage: Double,
    override val voteCount: Int,
) : ContentDetail
