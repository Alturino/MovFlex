package com.onirutla.movflex.core.domain.model

import com.onirutla.movflex.core.data.ItemType
import com.onirutla.movflex.core.data.source.local.entities.FavoriteEntity

interface Content {
    val id: Int
    val backdropPath: String
    val title: String
    val description: String
    val posterPath: String
    val type: ItemType
    val voteAverage: Double
    val isFavorite: Boolean
}

fun Content.toEntity() = FavoriteEntity(
    id = id,
    backdropPath = backdropPath,
    title = title,
    description = description,
    posterPath = posterPath,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
)
