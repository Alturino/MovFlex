package com.onirutla.movflex.core.domain.model

data class Season(
    val airDate: String,
    val episodes: List<Episode>,
    val _id: String,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
)
