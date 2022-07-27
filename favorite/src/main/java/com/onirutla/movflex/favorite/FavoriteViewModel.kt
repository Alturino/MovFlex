package com.onirutla.movflex.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.core.domain.usecase.favorite.FavoriteUseCase

class FavoriteViewModel constructor(
    favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    val favorite = favoriteUseCase.getFavorite().cachedIn(viewModelScope)

}
