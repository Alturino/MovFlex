package com.onirutla.movflex.di

import com.onirutla.movflex.source.remote.movie.MovieRemoteDataSource
import com.onirutla.movflex.source.remote.movie.MovieRemoteDataSourceImpl
import com.onirutla.movflex.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.source.remote.tv.TvRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindTvRemoteDataSource(remoteDataSourceImpl: TvRemoteDataSourceImpl): TvRemoteDataSource
}