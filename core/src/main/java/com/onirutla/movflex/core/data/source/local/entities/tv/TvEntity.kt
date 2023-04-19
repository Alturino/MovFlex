package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "in_production")
    val inProduction: Boolean,
    @ColumnInfo(name = "last_air_date")
    val lastAirDate: String,
    val name: String,
    @ColumnInfo(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @ColumnInfo(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    val status: String,
    val tagline: String,
    val type: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
)
