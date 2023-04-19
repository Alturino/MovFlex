package com.onirutla.movflex.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.local.dao.TvDao
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.data.source.local.entities.shared.GenreEntity
import com.onirutla.movflex.core.data.source.local.entities.shared.ProductionCompanyEntity
import com.onirutla.movflex.core.data.source.local.entities.shared.ProductionCountryEntity
import com.onirutla.movflex.core.data.source.local.entities.shared.SpokenLanguageEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.CreatedByEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.CrewEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.EpisodeEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.EpisodeRuntimeEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.GuestStarEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.LanguageEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.LastEpisodeToAirEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.NetworkEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.OriginCountryEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.SeasonEntity
import com.onirutla.movflex.core.data.source.local.entities.tv.TvEntity

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class,
        TvEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
        CreatedByEntity::class,
        CrewEntity::class,
        EpisodeEntity::class,
        EpisodeRuntimeEntity::class,
        GuestStarEntity::class,
        LanguageEntity::class,
        LastEpisodeToAirEntity::class,
        NetworkEntity::class,
        OriginCountryEntity::class,
        SeasonEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovFlexDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val tvDao: TvDao
}
