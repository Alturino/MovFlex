package com.onirutla.movflex.tv.domain.model

import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType

data class TvContent(
    override val backdropPath: String,
    override val releaseDate: String,
    override val genre: Genre,
    override val id: Int,
    override val name: String,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val originalName: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String,
    override val voteAverage: Double,
    override val voteCount: Int,
    override val isFavorite: Boolean,
    override val itemType: ItemType,
) : Content
