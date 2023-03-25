package com.onirutla.movflex.movie.domain.model

import com.onirutla.movflex.core.data.ItemType
import com.onirutla.movflex.core.domain.model.Content

data class MovieContent(
    override val id: Int = 0,
    override val backdropPath: String = "",
    override val title: String = "",
    override val description: String = "",
    override val posterPath: String = "",
    override val voteAverage: Double = 0.0,
    override val isFavorite: Boolean = false,
    override val type: ItemType = ItemType.Movie,
) : Content
