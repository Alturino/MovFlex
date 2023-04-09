package com.onirutla.movflex.tv.core.remote

import android.util.Log
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponseDetail
import com.onirutla.movflex.tv.domain.remote.TvRemoteDataSource
import javax.inject.Inject

class TvRemoteDataSourceImpl @Inject constructor(
    private val tvApiService: TvApiService,
) : TvRemoteDataSource {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override suspend fun getTvPopular(page: Int): List<TvResponse> = try {
        val response = tvApiService.getTvPopular(page)
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

    override suspend fun getTvOnTheAir(page: Int): List<TvResponse> = try {
        val response = tvApiService.getTvOnTheAir(page)
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

    override suspend fun getTvTopRated(page: Int): List<TvResponse> = try {
        val response = tvApiService.getTvTopRated(page)
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

    override suspend fun getTvDetail(tvId: Int): TvResponseDetail? = try {
        val response = tvApiService.getTvDetail(tvId)
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


    override suspend fun getTvAiringToday(page: Int): List<TvResponse> = try {
        val response = tvApiService.getTvAiringToday(page)
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
