package com.onirutla.movflex.movie.domain.remote

import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponseDetail

interface MovieRemoteDataSource {
    suspend fun getMoviePopular(page: Int = 1): List<MovieResponse>

    suspend fun getMovieNowPlaying(page: Int = 1): List<MovieResponse>

    suspend fun getMovieTopRated(page: Int = 1): List<MovieResponse>

    suspend fun getMovieDetail(id: Int): MovieResponseDetail

    suspend fun getMovieUpcoming(page: Int = 1): List<MovieResponse>
}
