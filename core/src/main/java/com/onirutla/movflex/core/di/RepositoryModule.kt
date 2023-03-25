package com.onirutla.movflex.core.di

import com.onirutla.movflex.core.data.repository.favorite.FavoriteRepositoryImpl
import com.onirutla.movflex.core.data.repository.tv.TvRepositoryImpl
import com.onirutla.movflex.core.domain.repository.FavoriteRepository
import com.onirutla.movflex.core.domain.repository.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTvRepository(repositoryImpl: TvRepositoryImpl): TvRepository

    @Binds
    abstract fun bindFavoriteRepository(repositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

}
