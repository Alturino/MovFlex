package com.onirutla.movflex.data.repository.favorite

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<FavoriteEntity>>
    suspend fun setFavorite(movie: FavoriteEntity)
}