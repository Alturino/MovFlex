package com.onirutla.movflex.movie.di

import com.onirutla.movflex.movie.core.remote.MovieRemoteDataSourceImpl
import com.onirutla.movflex.movie.core.repository.MovieRepositoryImpl
import com.onirutla.movflex.movie.core.usecase.MovieMoreUseCaseImpl
import com.onirutla.movflex.movie.core.usecase.MovieUseCaseImpl
import com.onirutla.movflex.movie.domain.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.repository.MovieRepository
import com.onirutla.movflex.movie.domain.usecase.MovieMoreUseCase
import com.onirutla.movflex.movie.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieImplModule {

    @Binds
    abstract fun bindMovieMoreUseCase(movieMoreUseCaseImpl: MovieMoreUseCaseImpl): MovieMoreUseCase

    @Binds
    abstract fun bindMovieUseCase(movieUseCaseImpl: MovieUseCaseImpl): MovieUseCase

    @Binds
    abstract fun bindMovieRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}
