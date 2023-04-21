package com.onirutla.movflex.core.domain.model

data class LastEpisodeToAir(
    val airDate: String = "",
    val episodeNumber: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val productionCode: String = "",
    val runtime: Int = 0,
    val seasonNumber: Int = 0,
    val showId: Int = 0,
    val stillPath: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
