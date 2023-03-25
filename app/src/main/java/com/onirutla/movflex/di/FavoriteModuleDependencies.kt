package com.onirutla.movflex.di

import com.onirutla.movflex.core.domain.usecase.favorite.FavoriteUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun favoriteUseCase(): FavoriteUseCase

}
