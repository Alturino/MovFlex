package com.onirutla.movflex.data.repository.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemDto
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): LiveData<PagingData<ItemDto>>
    fun getTvPopularHome(): Flow<List<ItemDto>>

    fun getTvOnTheAirPaging(): LiveData<PagingData<ItemDto>>
    fun getTvOnTheAirHome(): Flow<List<ItemDto>>

    fun getTvTopRatedPaging(): LiveData<PagingData<ItemDto>>
    fun getTvTopRatedHome(): Flow<List<ItemDto>>

    fun getTvAiringTodayPaging(): LiveData<PagingData<ItemDto>>
    fun getTvAiringTodayHome(): Flow<List<ItemDto>>

    fun getTvDetail(id: Int): Flow<FavoriteEntity>
}