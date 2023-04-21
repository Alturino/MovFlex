package com.onirutla.movflex.tv.core.remote

import android.util.Log
import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import javax.inject.Inject

class TvRemoteDataSource @Inject constructor(
    private val apiService: TvApiService,
) {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    suspend fun getTvPopular(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvPopular(page)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!.results
        } else {
            Log.d(TAG, "${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        emptyList()
    }

    suspend fun getTvOnTheAir(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvOnTheAir(page)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!.results
        } else {
            Log.d(TAG, "${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        emptyList()
    }

    suspend fun getTvTopRated(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvTopRated(page)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!.results
        } else {
            Log.d(TAG, "${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        emptyList()
    }

    suspend fun getTvDetail(tvId: Int): TvDetailResponse? = try {
        val response = apiService.getTvDetail(tvId)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!
        } else {
            Log.d(TAG, "${response.body()}")
            null
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        null
    }


    suspend fun getTvAiringToday(page: Int = 1): List<TvResponse> = try {
        val response = apiService.getTvAiringToday(page)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!.results
        } else {
            Log.d(TAG, "${response.errorBody()}")
            emptyList()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        emptyList()
    }

}
