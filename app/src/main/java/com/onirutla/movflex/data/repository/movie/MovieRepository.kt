package com.onirutla.movflex.data.repository.movie

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface MovieRepository {

    fun getMoviePopularPaging(): Flow<PagingData<MovieResponse>>
    fun getMoviePopularHome(): Flow<List<MovieResponse>>

    fun getMovieNowPlayingPaging(): Flow<PagingData<MovieResponse>>
    fun getMovieNowPlayingHome(): Flow<List<MovieResponse>>

    fun getMovieTopRatedPaging(): Flow<PagingData<MovieResponse>>
    fun getMovieTopRatedHome(): Flow<List<MovieResponse>>

    fun getMovieUpcomingPaging(): Flow<PagingData<MovieResponse>>
    fun getMovieUpcomingHome(): Flow<List<MovieResponse>>

    fun getMovieDetail(): Flow<MovieResponseDetail>
}