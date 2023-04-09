package com.onirutla.movflex.core.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.FavoriteContent
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val genre: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "item_type")
    val itemType: ItemType,
)

fun FavoriteEntity.toContent(): Content = FavoriteContent(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genre = Genre(-1, genre),
    id = id,
    name = name,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = true,
    originCountry = emptyList(),
    itemType = itemType
)
