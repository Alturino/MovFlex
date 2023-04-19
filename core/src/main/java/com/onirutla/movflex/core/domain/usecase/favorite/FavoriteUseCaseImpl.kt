package com.onirutla.movflex.core.domain.usecase.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : FavoriteUseCase {

    override fun getFavorite(): LiveData<PagingData<Favorite>> = favoriteRepository.getFavorite()
        .asLiveData()

    override suspend fun setFavorite(content: Favorite) =
        favoriteRepository.setFavorite(content)

}
