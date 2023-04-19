package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class LanguageEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "show_id")
    val showId: Int,
    val language: String,
)
