package com.onirutla.movflex.core.domain.util

import com.onirutla.movflex.core.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.core.domain.model.Content

fun Content.toEntity() = FavoriteEntity(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    name = name,
    voteCount = voteCount,
    voteAverage = voteAverage,
    posterPath = posterPath,
    popularity = popularity,
    overview = overview,
    genre = genre.name,
    originalName = originalName,
    id = id,
    originalLanguage = originalLanguage,
    itemType = itemType,
)
