package com.onirutla.movflex.domain.di

import com.onirutla.movflex.domain.favorite.FavoriteRepository
import com.onirutla.movflex.domain.favorite.FavoriteRepositoryImpl
import com.onirutla.movflex.domain.repository.movie.MovieRepository
import com.onirutla.movflex.domain.repository.movie.MovieRepositoryImpl
import com.onirutla.movflex.domain.repository.tv.TvRepository
import com.onirutla.movflex.domain.repository.tv.TvRepositoryImpl
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
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindTvRepository(repositoryImpl: TvRepositoryImpl): TvRepository

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteRepository(repositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}