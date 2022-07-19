package com.onirutla.movflex.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import com.onirutla.movflex.model.Content
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopularPaging(): LiveData<PagingData<Content>>
    fun getMoviePopularHome(): Flow<List<Content>>

    fun getMovieNowPlayingPaging(): LiveData<PagingData<Content>>
    fun getMovieNowPlayingHome(): Flow<List<Content>>

    fun getMovieTopRatedPaging(): LiveData<PagingData<Content>>
    fun getMovieTopRatedHome(): Flow<List<Content>>

    fun getMovieUpcomingPaging(): LiveData<PagingData<Content>>
    fun getMovieUpcomingHome(): Flow<List<Content>>

    fun getMovieDetail(id: Int): Flow<FavoriteEntity>
}