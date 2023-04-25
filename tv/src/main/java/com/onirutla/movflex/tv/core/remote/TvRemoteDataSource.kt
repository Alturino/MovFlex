package com.onirutla.movflex.tv.core.remote

import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import timber.log.Timber
import javax.inject.Inject

class TvRemoteDataSource @Inject constructor(
    private val apiService: TvApiService,
) {

    suspend fun getTvPopular(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvPopular(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvOnTheAir(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvOnTheAir(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvTopRated(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvTopRated(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

    suspend fun getTvDetail(tvId: Int): TvDetailResponse? = try {
        val response = apiService.getTvDetail(tvId)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!
        } else {
            Timber.d("${response.body()}")
            null
        }
    } catch (e: Exception) {
        Timber.d(e)
        null
    }


    suspend fun getTvAiringToday(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvAiringToday(page)
        if (response.isSuccessful) {
            Timber.d("${response.body()}")
            response.body()!!.results
        } else {
            Timber.d("${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Timber.d(e)
        emptyList()
    }

}
