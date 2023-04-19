package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season")
data class SeasonEntity(
    @ColumnInfo(name = "air_date")
    val airDate: String,
    val _id: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "season_number")
    val seasonNumber: Int,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)
