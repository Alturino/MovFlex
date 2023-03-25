package com.onirutla.movflex.core.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onirutla.movflex.core.data.ItemType
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.FavoriteContent

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
    val isFavorite: Boolean = false,
)

fun FavoriteEntity.toContent(): Content =
    FavoriteContent(
        id = id,
        backdropPath = backdropPath,
        title = title,
        description = description,
        posterPath = posterPath,
        voteAverage = voteAverage,
        isFavorite = isFavorite,
        type = type,
    )
