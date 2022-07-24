package com.onirutla.movflex.data.source.remote.movie

import android.util.Log
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail
import com.onirutla.movflex.data.source.remote.service.MovieApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApiService: MovieApiService
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

    override suspend fun getMovieDetail(id: Int): MovieResponseDetail = try {
        val response = movieApiService.getMovieDetail(id)
        if (response.isSuccessful) {
            Log.d(TAG, "${response.body()}")
            response.body()!!
        } else {
            Log.d(TAG, "${response.errorBody()}")
            MovieResponseDetail()
        }
    } catch (e: Exception) {
        Log.d(TAG, "$e")
        MovieResponseDetail()
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