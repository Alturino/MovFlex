package com.onirutla.movflex.di

import com.onirutla.movflex.BuildConfig
import com.onirutla.movflex.data.source.remote.service.MovieApiService
import com.onirutla.movflex.data.source.remote.service.TvApiService
import com.onirutla.movflex.util.Constants.API_TOKEN
import com.onirutla.movflex.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideMoshiBuilder(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $API_TOKEN")
            .build()
        it.proceed(request)
    }

    @Singleton
    @Provides
    fun provideLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideTvApiService(retrofit: Retrofit): TvApiService =
        retrofit.create(TvApiService::class.java)
}