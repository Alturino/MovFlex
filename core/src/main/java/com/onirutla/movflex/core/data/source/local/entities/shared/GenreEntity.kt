package com.onirutla.movflex.core.data.source.local.entities.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "show_id")
    val showId: Int,
    val name: String,
)
