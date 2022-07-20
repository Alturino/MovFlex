package com.onirutla.movflex.di

import com.onirutla.movflex.data.source.remote.movie.MovieRemoteDataSource
import com.onirutla.movflex.data.source.remote.movie.MovieRemoteDataSourceImpl
import com.onirutla.movflex.data.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.data.source.remote.tv.TvRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindTvRemoteDataSource(remoteDataSourceImpl: TvRemoteDataSourceImpl): TvRemoteDataSource
}