package com.onirutla.movflex.core.di

import android.content.Context
import androidx.room.Room
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.local.dao.TvDao
import com.onirutla.movflex.core.data.source.local.db.MovFlexDatabase
import com.onirutla.movflex.core.util.Constants.DB_PASSPHRASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovFlexDatabase {
        val passPhrase = SQLiteDatabase.getBytes(DB_PASSPHRASE.toCharArray())
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(
            context,
            MovFlexDatabase::class.java,
            "movflex.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
//            .openHelperFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: MovFlexDatabase): MovieDao = db.movieDao

    @Singleton
    @Provides
    fun provideTvDao(db: MovFlexDatabase): TvDao = db.tvDao
}
