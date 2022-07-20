package com.onirutla.movflex.di

import com.onirutla.movflex.domain.usecase.favorite.FavoriteUseCase
import com.onirutla.movflex.domain.usecase.favorite.FavoriteUseCaseImpl
import com.onirutla.movflex.domain.usecase.movie.MovieMoreUseCase
import com.onirutla.movflex.domain.usecase.movie.MovieMoreUseCaseImpl
import com.onirutla.movflex.domain.usecase.movie.MovieUseCase
import com.onirutla.movflex.domain.usecase.movie.MovieUseCaseImpl
import com.onirutla.movflex.domain.usecase.tv.TvMoreUseCase
import com.onirutla.movflex.domain.usecase.tv.TvMoreUseCaseImpl
import com.onirutla.movflex.domain.usecase.tv.TvUseCase
import com.onirutla.movflex.domain.usecase.tv.TvUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMovieMoreUseCase(movieMoreUseCaseImpl: MovieMoreUseCaseImpl): MovieMoreUseCase

    @Binds
    abstract fun bindMovieUseCase(movieUseCaseImpl: MovieUseCaseImpl): MovieUseCase

    @Binds
    abstract fun bindFavoriteUseCase(favoriteUseCaseImpl: FavoriteUseCaseImpl): FavoriteUseCase

    @Binds
    abstract fun bindTvMoreUseCase(tvMoreUseCaseImpl: TvMoreUseCaseImpl): TvMoreUseCase

    @Binds
    abstract fun bindTvUseCase(tvUseCaseImpl: TvUseCaseImpl): TvUseCase
}