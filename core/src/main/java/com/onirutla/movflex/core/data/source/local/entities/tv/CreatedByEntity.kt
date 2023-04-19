package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "created_by")
data class CreatedByEntity(
    @ColumnInfo(name = "credit_id")
    val creditId: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)
