package com.onirutla.movflex.data.repository.tv

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.tv.TvResponseDetail
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): Flow<PagingData<ItemResponse>>
    fun getTvPopularHome(): Flow<List<ItemResponse>>

    fun getTvOnTheAirPaging(): Flow<PagingData<ItemResponse>>
    fun getTvOnTheAirHome(): Flow<List<ItemResponse>>

    fun getTvTopRatedPaging(): Flow<PagingData<ItemResponse>>
    fun getTvTopRatedHome(): Flow<List<ItemResponse>>

    fun getTvAiringTodayPaging(): Flow<PagingData<ItemResponse>>
    fun getTvAiringTodayHome(): Flow<List<ItemResponse>>

    fun getTvDetail(id: Int): Flow<TvResponseDetail>
}