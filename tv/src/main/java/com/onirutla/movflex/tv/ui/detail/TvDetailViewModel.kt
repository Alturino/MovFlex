package com.onirutla.movflex.tv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.TvDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository,
) : ViewModel() {

    private val _tvId = MutableLiveData<Int>()

    val tvDetail: LiveData<TvDetail> = _tvId.switchMap {
        tvRepository.getTvDetail(it).asLiveData(viewModelScope.coroutineContext)
    }

    fun setFavorite() {
        viewModelScope.launch {
            _tvId.value?.let { id ->
                tvRepository.getTvDetail(id).collect {
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
