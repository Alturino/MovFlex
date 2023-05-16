package com.onirutla.movflex

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .diskCache {
            DiskCache.Builder()
                .directory(applicationContext.cacheDir.resolve("image_cache"))
                .build()
        }.memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(0.25)
                .build()
        }.okHttpClient(okHttpClient.newBuilder().build())
        .build()
}
