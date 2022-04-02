package com.onirutla.movflex.data.repository.tv

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.remote.response.tv.TvResponse
import com.onirutla.movflex.data.source.remote.response.tv.TvResponseDetail
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): Flow<PagingData<TvResponse>>
    fun getTvPopularHome(): Flow<List<TvResponse>>

    fun getTvOnTheAirPaging(): Flow<PagingData<TvResponse>>
    fun getTvOnTheAirHome(): Flow<List<TvResponse>>

    fun getTvTopRatedPaging(): Flow<PagingData<TvResponse>>
    fun getTvTopRatedHome(): Flow<List<TvResponse>>

    fun getTvAiringTodayPaging(): Flow<PagingData<TvResponse>>
    fun getTvAiringTodayHome(): Flow<List<TvResponse>>

    fun getTvDetail(): Flow<TvResponseDetail>
}