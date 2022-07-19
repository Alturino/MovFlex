package com.onirutla.movflex.ui.tv.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.repository.favorite.FavoriteRepository
import com.onirutla.movflex.data.repository.tv.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _tvId = MutableLiveData<Int>()

    val tvDetail = _tvId.switchMap {
        tvRepository.getTvDetail(it).asLiveData(viewModelScope.coroutineContext)
    }

    fun setFavorite() {
        viewModelScope.launch {
            _tvId.value?.let { id ->
                tvRepository.getTvDetail(id).collect {
                    favoriteRepository.setFavorite(it)
                }
            }
        }
    }

    fun getTvDetail(id: Int) {
        viewModelScope.launch {
            _tvId.value = id
        }
    }

}