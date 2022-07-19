package com.onirutla.movflex.model

data class Content(
    val id: Int = 0,
    val backdropPath: String = "",
    val title: String = "",
    val posterPath: String = "",
    val voteAverage: Float = 0f,
    val isFavorite: Boolean = false
)
