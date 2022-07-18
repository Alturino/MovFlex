package com.onirutla.movflex.ui.movie.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.domain.favorite.FavoriteRepository
import com.onirutla.movflex.domain.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _movieId = MutableLiveData<Int>()

    val movieDetail = _movieId.switchMap {
        movieRepository.getMovieDetail(it).asLiveData(viewModelScope.coroutineContext)
    }

    fun getMovieDetail(id: Int) {
        _movieId.value = id
    }

    fun setFavorite() {
        viewModelScope.launch {
            _movieId.value?.let { id ->
                movieRepository.getMovieDetail(id).collect { movie ->
                    favoriteRepository.setFavorite(movie)
                }
            }
        }
    }

}