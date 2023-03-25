package com.onirutla.movflex.core.di

import com.onirutla.movflex.core.data.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.core.data.source.remote.tv.TvRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindTvRemoteDataSource(remoteDataSourceImpl: TvRemoteDataSourceImpl): TvRemoteDataSource
}
