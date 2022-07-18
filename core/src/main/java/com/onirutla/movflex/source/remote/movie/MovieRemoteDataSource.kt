package com.onirutla.movflex.source.remote.movie

import com.onirutla.movflex.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.source.remote.response.movie.MovieResponseDetail

interface MovieRemoteDataSource {
    suspend fun getMoviePopular(page: Int = 1): List<MovieResponse>

    suspend fun getMovieNowPlaying(page: Int = 1): List<MovieResponse>

    suspend fun getMovieTopRated(page: Int = 1): List<MovieResponse>

    suspend fun getMovieDetail(id: Int): MovieResponseDetail

    suspend fun getMovieUpcoming(page: Int = 1): List<MovieResponse>
}