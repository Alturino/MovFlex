package com.onirutla.movflex.di

import com.onirutla.movflex.core.BuildConfig
import com.onirutla.movflex.core.BuildConfig.CERTIFICATE_0
import com.onirutla.movflex.core.BuildConfig.CERTIFICATE_1
import com.onirutla.movflex.core.BuildConfig.CERTIFICATE_2
import com.onirutla.movflex.core.BuildConfig.HOST_NAME
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
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        val interceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .build()
            it.proceed(request)
        }

        val certificatePinner = CertificatePinner.Builder()
            .add(HOST_NAME, CERTIFICATE_0)
            .add(HOST_NAME, CERTIFICATE_1)
            .add(HOST_NAME, CERTIFICATE_2)
            .build()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .certificatePinner(certificatePinner)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideTvApiService(retrofit: Retrofit): TvApiService =
        retrofit.create(TvApiService::class.java)
}