package com.onirutla.movflex.domain.repository.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getTvPopularPaging(): LiveData<PagingData<Content>>
    fun getTvPopularHome(): Flow<List<Content>>

    fun getTvOnTheAirPaging(): LiveData<PagingData<Content>>
    fun getTvOnTheAirHome(): Flow<List<Content>>

    fun getTvTopRatedPaging(): LiveData<PagingData<Content>>
    fun getTvTopRatedHome(): Flow<List<Content>>

    fun getTvAiringTodayPaging(): LiveData<PagingData<Content>>
    fun getTvAiringTodayHome(): Flow<List<Content>>

    fun getTvDetail(id: Int): Flow<FavoriteEntity>
}