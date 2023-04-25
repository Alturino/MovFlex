package com.onirutla.movflex.movie.ui.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.core.usecase.MovieMoreUseCase
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.util.toDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieMoreViewModel @Inject constructor(
    private val movieMoreUseCase: MovieMoreUseCase,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _movieType = MutableLiveData<MovieType>()

    val movieMore = _movieType.switchMap {
        movieMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getMovieByCategory(movieType: MovieType) {
        _movieType.value = movieType
    }

    fun setFavorite(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movieRepository.setFavorite(movie.toDetail())
            }
        }
    }
}
