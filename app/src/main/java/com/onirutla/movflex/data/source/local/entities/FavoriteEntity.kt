package com.onirutla.movflex.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",
    @ColumnInfo(name = "name")
    val title: String = "",
    @ColumnInfo(name = "overview")
    val description: String = "",
    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = "type")
    val type: ItemType = ItemType.Movie,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = true
)

enum class ItemType(val type: String) {
    Movie("Movie"),
    Tv("Tv")
}
