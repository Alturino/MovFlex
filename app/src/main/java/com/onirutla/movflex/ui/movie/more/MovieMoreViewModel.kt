package com.onirutla.movflex.ui.movie.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.domain.usecase.movie.MovieMoreUseCase
import com.onirutla.movflex.domain.model.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieMoreViewModel @Inject constructor(
    private val movieMoreUseCase: MovieMoreUseCase
) : ViewModel() {

    private val _movieType = MutableLiveData<MovieType>()

    val movieMore = _movieType.switchMap {
        movieMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getMovieByCategory(movieType: MovieType) {
        _movieType.value = movieType
    }


}