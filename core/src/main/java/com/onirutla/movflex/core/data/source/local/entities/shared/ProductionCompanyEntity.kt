package com.onirutla.movflex.core.data.source.local.entities.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_company")
data class ProductionCompanyEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "logo_path")
    val logoPath: String,
    val name: String,
    @ColumnInfo(name = "origin_country")
    val originCountry: String,
    @ColumnInfo(name = "show_id")
    val showId: Int,
)
