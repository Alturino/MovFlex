package com.onirutla.movflex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.domain.usecase.favorite.FavoriteUseCase

class FavoriteViewModel constructor(
    favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    val favorite = favoriteUseCase.getFavorite().cachedIn(viewModelScope)

}
