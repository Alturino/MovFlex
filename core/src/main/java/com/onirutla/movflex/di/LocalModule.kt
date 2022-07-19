package com.onirutla.movflex.di

import android.content.Context
import androidx.room.Room
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.db.MovFlexDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovFlexDatabase =
        Room.databaseBuilder(
            context,
            MovFlexDatabase::class.java,
            "movflex_database"
        ).build()

    @Singleton
    @Provides
    fun provideFavoriteDao(db: MovFlexDatabase): FavoriteDao = db.favoriteDao

}