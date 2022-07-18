package com.onirutla.movflex.data.repository.favorite

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<Content>>
    suspend fun setFavorite(content: Content)
}