package com.onirutla.movflex.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopularPaging(): LiveData<PagingData<ItemDto>>
    fun getMoviePopularHome(): Flow<List<ItemDto>>

    fun getMovieNowPlayingPaging(): LiveData<PagingData<ItemDto>>
    fun getMovieNowPlayingHome(): Flow<List<ItemDto>>

    fun getMovieTopRatedPaging(): LiveData<PagingData<ItemDto>>
    fun getMovieTopRatedHome(): Flow<List<ItemDto>>

    fun getMovieUpcomingPaging(): LiveData<PagingData<ItemDto>>
    fun getMovieUpcomingHome(): Flow<List<ItemDto>>

    fun getMovieDetail(id: Int): Flow<FavoriteEntity>
}