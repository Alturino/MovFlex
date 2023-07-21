package com.onirutla.movflex.movie.ui.screen.see_more

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.onirutla.movflex.movie.core.usecase.MovieSeeMoreUseCase
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieSeeMoreViewModel @Inject constructor(
    private val useCase: MovieSeeMoreUseCase,
) : ViewModel() {

    private val _movieType = MutableStateFlow<MovieType?>(null)

    var movieId = 0

    val movieMore: Flow<PagingData<Movie>> = _movieType.flatMapLatest { movieType ->
        useCase(movieType ?: MovieType.MOVIE_UPCOMING)
    }

    fun getMovieByCategory(movieType: MovieType) {
        _movieType.value = movieType
    }

    fun getMovieByCategory(movieType: String) {
        val enum = MovieType.values().first { it.value == movieType }
        _movieType.value = enum
    }
}
