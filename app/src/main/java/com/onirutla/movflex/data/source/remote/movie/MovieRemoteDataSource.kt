package com.onirutla.movflex.data.source.remote.movie

import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail

interface MovieRemoteDataSource {
    suspend fun getMoviePopular(page: Int = 1): List<ItemDto>

    suspend fun getMovieNowPlaying(page: Int = 1): List<ItemDto>

    suspend fun getMovieTopRated(page: Int = 1): List<ItemDto>

    suspend fun getMovieDetail(id: Int): MovieResponseDetail

    suspend fun getMovieUpcoming(page: Int = 1): List<ItemDto>
}