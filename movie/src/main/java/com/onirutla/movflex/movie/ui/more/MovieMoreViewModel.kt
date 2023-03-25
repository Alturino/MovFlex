package com.onirutla.movflex.movie.ui.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.domain.usecase.MovieMoreUseCase
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
