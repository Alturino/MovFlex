package com.onirutla.movflex.core.domain.model.shared

import com.onirutla.movflex.core.data.source.remote.response.shared.EpisodeResponse

data class Season(
    val airDate: String,
    val episodes: List<EpisodeResponse>,
    val _id: String,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
)
