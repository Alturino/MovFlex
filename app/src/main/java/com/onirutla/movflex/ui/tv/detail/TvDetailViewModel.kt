package com.onirutla.movflex.ui.tv.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _tvId = MutableSharedFlow<Int>()

    val tvDetail = _tvId.flatMapLatest {
        tvRepository.getTvDetail(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MovieResponseDetail()
    )

    fun getTvDetail(id: Int) {
        viewModelScope.launch {
            _tvId.emit(id)
        }
    }

}