package com.onirutla.movflex.tv.domain.model

data class Tv(
    val backdropPath: String = "",
    val firstAirDate: String = "",
    val genres: String = "",
    val id: Int = 0,
    val name: String = "",
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
