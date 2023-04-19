package com.onirutla.movflex.tv.domain.model

import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType

data class TvContent(
    override val adult: Boolean,
    override val id: Int,
    override val originalTitle: String,
    val firstAirDate: String,
    override val title: String,
    override val video: Boolean,
    override val backdropPath: String,
    override val genres: List<Genre>,
    override val name: String,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val originalName: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String,
    override val voteAverage: Double,
    override val voteCount: Int,
    override val itemType: ItemType = ItemType.Tv,
) : Content {
    override val releaseDate: String
        get() = firstAirDate
}
