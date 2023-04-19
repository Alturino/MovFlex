package com.onirutla.movflex.core.data.source.local.entities.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_country")
data class ProductionCountryEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "iso_31661")
    val iso31661: String,
    val name: String,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)
