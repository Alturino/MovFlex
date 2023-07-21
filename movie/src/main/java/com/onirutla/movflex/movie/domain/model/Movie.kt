package com.onirutla.movflex.movie.domain.model

data class Movie(
    val adult: Boolean = false,
    val id: Int = 0,
    val originalTitle: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val backdropPath: String = "",
    val genres: String = "",
    val name: String = "",
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val isFavorite: Boolean = false,
)

