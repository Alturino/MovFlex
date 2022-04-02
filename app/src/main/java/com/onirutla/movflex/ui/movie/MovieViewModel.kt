package com.onirutla.movflex.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.ui.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val popularMovie = movieRepository.getMoviePopularHome()
    private val upcomingMovie = movieRepository.getMovieUpcomingHome()
    private val topRatedMovie = movieRepository.getMovieTopRatedHome()
    private val nowPlayingMovie = movieRepository.getMovieNowPlayingHome()

    val movie = combine(
        popularMovie,
        upcomingMovie,
        topRatedMovie,
        nowPlayingMovie
    ) { popular, upcoming, topRated, nowPlaying ->

        val popularCategory = Category("Popular", popular)
        val upcomingCategory = Category("Upcoming", upcoming)
        val topRatedCategory = Category("Top Rated", topRated)
        val nowPlayingCategory = Category("Now Playing", nowPlaying)

        listOf(
            popularCategory,
            upcomingCategory,
            topRatedCategory,
            nowPlayingCategory
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

}
