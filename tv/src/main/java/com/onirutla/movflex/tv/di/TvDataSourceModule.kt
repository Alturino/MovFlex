package com.onirutla.movflex.tv.di

import com.onirutla.movflex.tv.domain.remote.TvRemoteDataSource
import com.onirutla.movflex.tv.core.remote.TvRemoteDataSourceImpl
import com.onirutla.movflex.tv.domain.repository.TvRepository
import com.onirutla.movflex.tv.core.repository.TvRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TvDataSourceModule {

    @Binds
    abstract fun bindTvRepository(repositoryImpl: TvRepositoryImpl): TvRepository

    @Binds
    abstract fun bindTvRemoteDataSource(remoteDataSourceImpl: TvRemoteDataSourceImpl): TvRemoteDataSource
}
