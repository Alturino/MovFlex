package com.onirutla.movflex.movie.domain.model

import com.onirutla.movflex.core.domain.model.Genre

data class Movie(
    val adult: Boolean,
    val id: Int,
    val originalTitle: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val backdropPath: String,
    val genres: List<Genre>,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
)

