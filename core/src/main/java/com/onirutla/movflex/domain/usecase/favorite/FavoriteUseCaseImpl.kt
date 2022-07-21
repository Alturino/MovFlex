package com.onirutla.movflex.domain.usecase.favorite

import androidx.lifecycle.asLiveData
import com.onirutla.movflex.domain.repository.FavoriteRepository
import com.onirutla.movflex.domain.model.Content
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : FavoriteUseCase {

    override fun getFavorite() = favoriteRepository.getFavorite().asLiveData()
    override suspend fun setFavorite(content: Content) = favoriteRepository.setFavorite(content)

}