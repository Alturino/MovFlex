package com.onirutla.movflex.core.domain.model.shared

data class Episode(
    val airDate: String,
    val crew: List<Crew>,
    val episodeNumber: Int,
    val guestStars: List<GuestStar>,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int,
)
