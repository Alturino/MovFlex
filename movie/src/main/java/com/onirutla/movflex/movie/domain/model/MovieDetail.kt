package com.onirutla.movflex.movie.domain.model

import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.SpokenLanguage

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genre: String,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)
