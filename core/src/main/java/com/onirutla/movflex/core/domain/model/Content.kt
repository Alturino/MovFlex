package com.onirutla.movflex.core.domain.model

import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType

interface Content {
    val backdropPath: String
    val releaseDate: String
    val genre: Genre
    val id: Int
    val name: String
    val originCountry: List<String>
    val originalLanguage: String
    val originalName: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val voteAverage: Double
    val voteCount: Int
    val isFavorite: Boolean
    val itemType: ItemType
}
