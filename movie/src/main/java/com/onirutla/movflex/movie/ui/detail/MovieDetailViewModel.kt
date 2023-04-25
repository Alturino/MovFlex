package com.onirutla.movflex.movie.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _movieId = MutableLiveData<Int>()

    val movieDetail = _movieId.switchMap {
        movieRepository.getMovieDetail(it).asLiveData(viewModelScope.coroutineContext)
    }

    val movieRecommendations = _movieId.switchMap {
        movieRepository.getMovieRecommendations(it).asLiveData(viewModelScope.coroutineContext)
    }

    val movieReviews = _movieId.switchMap {
        movieRepository.getMovieReviews(it).asLiveData(viewModelScope.coroutineContext)
    }

    val movieCasts = _movieId.switchMap {
        movieRepository.getMovieCasts(it).asLiveData(viewModelScope.coroutineContext)
    }

    val movieSimilar = _movieId.switchMap {
        movieRepository.getMovieSimilar(it).asLiveData(viewModelScope.coroutineContext)
    }

    val isFavorite = _movieId.switchMap {
        movieRepository.observeFavoriteState(it).asLiveData(viewModelScope.coroutineContext)
    }

    fun getMovieDetail(id: Int) {
        _movieId.value = id
    }

    fun setFavorite(movie: MovieDetail) {
        viewModelScope.launch {
            movieRepository.setFavorite(movie)
        }
    }


}
