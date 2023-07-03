package com.onirutla.movflex.movie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
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

    val isMovieFavorited: LiveData<Boolean> = movieDetail.map { it.isFavorite }

    val movieRecommendations: LiveData<List<Movie>> = _movieId.switchMap {
        liveData { emit(movieRepository.getMovieRecommendations(it)) }
    }

    val movieReviews: LiveData<List<Review>> = _movieId.switchMap {
        liveData { emit(movieRepository.getMovieReviews(it)) }
    }

    val movieCasts: LiveData<List<Cast>> = _movieId.switchMap {
        liveData { emit(movieRepository.getMovieCasts(it)) }
    }

    val movieSimilar: LiveData<List<Movie>> = _movieId.switchMap {
        liveData { emit(movieRepository.getMovieSimilar(it)) }
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
