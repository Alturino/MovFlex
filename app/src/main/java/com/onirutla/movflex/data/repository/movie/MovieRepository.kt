package com.onirutla.movflex.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopularPaging(): LiveData<PagingData<ItemResponse>>
    fun getMoviePopularHome(): Flow<List<ItemResponse>>

    fun getMovieNowPlayingPaging(): LiveData<PagingData<ItemResponse>>
    fun getMovieNowPlayingHome(): Flow<List<ItemResponse>>

    fun getMovieTopRatedPaging(): LiveData<PagingData<ItemResponse>>
    fun getMovieTopRatedHome(): Flow<List<ItemResponse>>

    fun getMovieUpcomingPaging(): LiveData<PagingData<ItemResponse>>
    fun getMovieUpcomingHome(): Flow<List<ItemResponse>>

    fun getMovieDetail(id: Int): Flow<FavoriteEntity>
}