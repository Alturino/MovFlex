package com.onirutla.movflex.core.domain.repository

import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<Content>>
    suspend fun setFavorite(content: Content)
}