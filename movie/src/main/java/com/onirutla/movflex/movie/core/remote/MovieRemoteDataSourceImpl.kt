package com.onirutla.movflex.movie.core.remote

import android.util.Log
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.domain.remote.MovieRemoteDataSource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApiService: MovieApiService,
) : MovieRemoteDataSource {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override suspend fun getMoviePopular(page: Int): List<MovieResponse> = try {
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

    override suspend fun getMovieNowPlaying(page: Int): List<MovieResponse> =
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

    override suspend fun getMovieTopRated(page: Int): List<MovieResponse> =
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

    override suspend fun getMovieDetail(id: Int): MovieDetailResponse? = try {
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

    override suspend fun getMovieUpcoming(page: Int): List<MovieResponse> =
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
