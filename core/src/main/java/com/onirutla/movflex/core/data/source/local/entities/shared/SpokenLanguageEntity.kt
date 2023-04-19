package com.onirutla.movflex.core.data.source.local.entities.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spoken_language")
data class SpokenLanguageEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "english_name")
    val englishName: String,
    @ColumnInfo(name = "iso_6391")
    val iso6391: String,
    val name: String,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)

