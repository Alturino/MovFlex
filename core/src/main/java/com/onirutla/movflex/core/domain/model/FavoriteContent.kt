package com.onirutla.movflex.core.domain.model

import com.onirutla.movflex.core.data.ItemType

data class FavoriteContent(
    override val id: Int,
    override val backdropPath: String,
    override val title: String,
    override val description: String,
    override val posterPath: String,
    override val voteAverage: Double,
    override val isFavorite: Boolean,
    override val type: ItemType,
) : Content

