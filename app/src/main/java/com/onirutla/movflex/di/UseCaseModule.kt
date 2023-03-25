package com.onirutla.movflex.di

import com.onirutla.movflex.core.domain.usecase.favorite.FavoriteUseCase
import com.onirutla.movflex.core.domain.usecase.favorite.FavoriteUseCaseImpl
import com.onirutla.movflex.tv.core.usecase.TvMoreUseCaseImpl
import com.onirutla.movflex.tv.core.usecase.TvUseCaseImpl
import com.onirutla.movflex.tv.domain.usecase.TvMoreUseCase
import com.onirutla.movflex.tv.domain.usecase.TvUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindFavoriteUseCase(favoriteUseCaseImpl: FavoriteUseCaseImpl): FavoriteUseCase

    @Binds
    abstract fun bindTvMoreUseCase(tvMoreUseCaseImpl: TvMoreUseCaseImpl): TvMoreUseCase

    @Binds
    abstract fun bindTvUseCase(tvUseCaseImpl: TvUseCaseImpl): TvUseCase
}
