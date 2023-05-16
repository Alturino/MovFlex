package com.onirutla.movflex.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.local.dao.TvDao
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.TvEntity

@Database(
    entities = [
        MovieEntity::class,
        TvEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MovFlexDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvDao: TvDao
}
