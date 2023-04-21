package com.onirutla.movflex.movie.core.remote

import android.util.Log
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApiService: MovieApiService,
) {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    suspend fun getMoviePopular(page: Int = 1): List<MovieResponse> = try {
        val response = movieApiService.getMoviePopular(page)
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

    suspend fun getMovieNowPlaying(page: Int = 1): List<MovieResponse> =
        try {
            val response = movieApiService.getMovieNowPlaying(page)
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

    suspend fun getMovieTopRated(page: Int = 1): List<MovieResponse> =
        try {
            val response = movieApiService.getMovieTopRated(page)
            if (response.isSuccessful) {
                Log.d(TAG, "${response.body()}")
                response.body()!!.results
            } else {
                Log.d(TAG, "${response.errorBody()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.d(this.javaClass.simpleName, "$e")
            emptyList()
        }

    suspend fun getMovieDetail(id: Int): MovieDetailResponse? = try {
        val response = movieApiService.getMovieDetail(id)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!
        } else {
            Log.d(TAG, "${response.errorBody()}")
            null
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        null
    }

    suspend fun getMovieUpcoming(page: Int = 1): List<MovieResponse> =
        try {
            val response = movieApiService.getMovieUpcoming(page)
            if (response.isSuccessful) {
                Log.d(this.javaClass.simpleName, "${response.body()}")
                response.body()!!.results
            } else {
                Log.d(this.javaClass.simpleName, "${response.errorBody()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.d(this.javaClass.simpleName, "$e")
            emptyList()
        }

}
