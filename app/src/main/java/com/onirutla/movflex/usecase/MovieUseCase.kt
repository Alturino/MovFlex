package com.onirutla.movflex.usecase

import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.ui.Category
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    movieRepository: MovieRepository
) {

    private val popularMovie = movieRepository.getMoviePopularHome()
    private val upcomingMovie = movieRepository.getMovieUpcomingHome()
    private val topRatedMovie = movieRepository.getMovieTopRatedHome()
    private val nowPlayingMovie = movieRepository.getMovieNowPlayingHome()

    val combinedCategory = combine(
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

    }
}