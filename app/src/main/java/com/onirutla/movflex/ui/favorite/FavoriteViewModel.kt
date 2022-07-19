package com.onirutla.movflex.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.data.repository.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    favoriteRepository: FavoriteRepository
) : ViewModel() {

    val favorite = favoriteRepository.getFavorite().cachedIn(viewModelScope)

}
