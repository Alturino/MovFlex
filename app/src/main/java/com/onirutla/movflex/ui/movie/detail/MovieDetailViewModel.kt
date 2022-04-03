package com.onirutla.movflex.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.repository.movie.MovieRepository
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
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieId = MutableSharedFlow<Int>(1)

    val movieDetail = _movieId.flatMapLatest {
        movieRepository.getMovieDetail(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MovieResponseDetail()
    )

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            _movieId.emit(id)
        }
    }

}