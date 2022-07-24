package com.onirutla.movflex.di

import com.onirutla.movflex.domain.repository.FavoriteRepository
import com.onirutla.movflex.data.repository.favorite.FavoriteRepositoryImpl
import com.onirutla.movflex.domain.repository.MovieRepository
import com.onirutla.movflex.data.repository.movie.MovieRepositoryImpl
import com.onirutla.movflex.domain.repository.TvRepository
import com.onirutla.movflex.data.repository.tv.TvRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindTvRepository(repositoryImpl: TvRepositoryImpl): TvRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(repositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

}