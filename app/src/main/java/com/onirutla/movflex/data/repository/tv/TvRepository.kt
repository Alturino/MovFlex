package com.onirutla.movflex.data.repository.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): LiveData<PagingData<ItemResponse>>
    fun getTvPopularHome(): Flow<List<ItemResponse>>

    fun getTvOnTheAirPaging(): LiveData<PagingData<ItemResponse>>
    fun getTvOnTheAirHome(): Flow<List<ItemResponse>>

    fun getTvTopRatedPaging(): LiveData<PagingData<ItemResponse>>
    fun getTvTopRatedHome(): Flow<List<ItemResponse>>

    fun getTvAiringTodayPaging(): LiveData<PagingData<ItemResponse>>
    fun getTvAiringTodayHome(): Flow<List<ItemResponse>>

    fun getTvDetail(id: Int): Flow<FavoriteEntity>

    suspend fun setFavorite(tv: FavoriteEntity)
}