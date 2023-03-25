package com.onirutla.movflex.movie.domain.repository

import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopularPaging(): Flow<PagingData<Content>>
    fun getMoviePopularHome(): Flow<List<Content>>

    fun getMovieNowPlayingPaging(): Flow<PagingData<Content>>
    fun getMovieNowPlayingHome(): Flow<List<Content>>

    fun getMovieTopRatedPaging(): Flow<PagingData<Content>>
    fun getMovieTopRatedHome(): Flow<List<Content>>

    fun getMovieUpcomingPaging(): Flow<PagingData<Content>>
    fun getMovieUpcomingHome(): Flow<List<Content>>

    fun getMovieDetail(id: Int): Flow<Content>
}
