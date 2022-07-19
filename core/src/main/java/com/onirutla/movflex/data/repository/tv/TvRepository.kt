package com.onirutla.movflex.data.repository.tv

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): Flow<PagingData<Content>>
    fun getTvPopularHome(): Flow<List<Content>>

    fun getTvOnTheAirPaging(): Flow<PagingData<Content>>
    fun getTvOnTheAirHome(): Flow<List<Content>>

    fun getTvTopRatedPaging(): Flow<PagingData<Content>>
    fun getTvTopRatedHome(): Flow<List<Content>>

    fun getTvAiringTodayPaging(): Flow<PagingData<Content>>
    fun getTvAiringTodayHome(): Flow<List<Content>>

    fun getTvDetail(id: Int): Flow<Content>
}