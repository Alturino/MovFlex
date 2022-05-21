package com.onirutla.movflex.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class MovFlexDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}
