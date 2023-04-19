package com.onirutla.movflex.core.data.source.local.entities.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origin_country")
data class OriginCountryEntity(
    @PrimaryKey
    val id: Int,
    val country: String,
    @ColumnInfo("show_id")
    val showId: Int,
)
