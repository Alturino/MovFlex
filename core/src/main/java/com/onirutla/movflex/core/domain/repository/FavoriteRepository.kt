package com.onirutla.movflex.core.domain.repository

import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.core.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<Content>>
    suspend fun setFavorite(content: Content)

    suspend fun isFavorite(id: Int): FavoriteEntity?
}
