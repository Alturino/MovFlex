package com.onirutla.movflex.domain.model

import com.onirutla.movflex.data.ItemType

data class Content(
    val id: Int = 0,
    val backdropPath: String = "",
    val title: String = "",
    val description: String = "",
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val type: ItemType = ItemType.Movie,
    val isFavorite: Boolean = false
)
