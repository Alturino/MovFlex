package com.onirutla.movflex.domain.repository

import androidx.paging.PagingData
import com.onirutla.movflex.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<Content>>
    suspend fun setFavorite(content: Content)
}