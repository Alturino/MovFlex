package com.onirutla.movflex.domain.favorite

import androidx.paging.PagingData
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorite(): Flow<PagingData<FavoriteEntity>>
    suspend fun setFavorite(movie: FavoriteEntity)
}