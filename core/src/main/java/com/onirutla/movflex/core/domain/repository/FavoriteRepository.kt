package com.onirutla.movflex.core.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository<T : Any, R> {
    fun getFavorite(): Flow<PagingData<T>>

    suspend fun setFavorite(content: R)
}
