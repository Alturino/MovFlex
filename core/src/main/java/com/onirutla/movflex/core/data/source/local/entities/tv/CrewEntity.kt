package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crew")
data class CrewEntity(
    val adult: Boolean,
    @ColumnInfo(name = "credit_id")
    val creditId: String,
    @ColumnInfo(name = "episode_id")
    val episodeId: Int,
    val department: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val job: String,
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
)
