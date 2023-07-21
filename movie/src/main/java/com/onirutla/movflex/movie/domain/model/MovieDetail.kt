package com.onirutla.movflex.movie.domain.model

import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.SpokenLanguage

data class MovieDetail(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Int = 0,
    val genres: String = "",
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val productionCountries: List<ProductionCountry> = listOf(),
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val isFavorite: Boolean = false,
)
