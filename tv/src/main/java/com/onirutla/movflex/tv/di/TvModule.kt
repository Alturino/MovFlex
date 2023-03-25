package com.onirutla.movflex.tv.di

import com.onirutla.movflex.tv.core.remote.TvApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvModule {

    @Singleton
    @Provides
    fun provideTvApiService(retrofit: Retrofit): TvApiService =
        retrofit.create(TvApiService::class.java)
}
