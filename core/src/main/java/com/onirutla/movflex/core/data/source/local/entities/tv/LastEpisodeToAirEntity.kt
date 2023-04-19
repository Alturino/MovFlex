package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_episode_to_air")
data class LastEpisodeToAirEntity(
    @ColumnInfo(name = "air_date")
    val airDate: String,
    @ColumnInfo(name = "episode_number")
    val episodeNumber: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    @ColumnInfo(name = "production_code")
    val productionCode: String,
    val runtime: Int,
    @ColumnInfo(name = "season_number")
    val seasonNumber: Int,
    @ColumnInfo(name = "show_id")
    val showId: Int,
    @ColumnInfo(name = "still_path")
    val stillPath: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
)
