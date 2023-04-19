package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_runtime")
data class EpisodeRuntimeEntity(
    @PrimaryKey
    val id: Int,
    val runtime: Int,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)
