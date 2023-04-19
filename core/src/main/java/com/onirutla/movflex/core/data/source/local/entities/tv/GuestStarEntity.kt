package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest_star")
data class GuestStarEntity(
    val adult: Boolean,
    val character: String,
    @ColumnInfo(name = "credit_id")
    val creditId: String,
    @ColumnInfo(name = "episode_id")
    val episodeId: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
)
