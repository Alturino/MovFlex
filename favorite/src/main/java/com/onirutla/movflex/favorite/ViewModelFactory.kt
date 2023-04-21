package com.onirutla.movflex.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
            FavoriteViewModel(favoriteUseCase) as T
        }
        else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
    }

}
