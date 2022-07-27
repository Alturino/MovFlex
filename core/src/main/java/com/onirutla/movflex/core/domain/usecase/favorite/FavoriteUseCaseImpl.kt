package com.onirutla.movflex.core.domain.usecase.favorite

import androidx.lifecycle.asLiveData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : FavoriteUseCase {

    override fun getFavorite() = favoriteRepository.getFavorite().asLiveData()
    override suspend fun setFavorite(content: Content) = favoriteRepository.setFavorite(content)

}