package com.onirutla.movflex.favorite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteTvViewModel @Inject constructor(
    private val repository: TvRepository,
) : ViewModel() {

    val tvFavorite: Flow<PagingData<Tv>> = repository.getTvFavorite()
        .cachedIn(viewModelScope)
}
