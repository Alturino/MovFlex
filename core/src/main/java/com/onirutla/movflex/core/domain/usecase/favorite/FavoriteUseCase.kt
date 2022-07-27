package com.onirutla.movflex.core.domain.usecase.favorite

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content

interface FavoriteUseCase {
    fun getFavorite(): LiveData<PagingData<Content>>
    suspend fun setFavorite(content: Content)
}