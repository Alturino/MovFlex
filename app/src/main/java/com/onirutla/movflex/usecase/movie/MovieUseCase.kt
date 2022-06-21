package com.onirutla.movflex.usecase.movie

import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.ui.SeeMore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    movieRepository: MovieRepository
) {
    private val popularMovie = movieRepository.getMoviePopularHome()
    private val upcomingMovie = movieRepository.getMovieUpcomingHome()
    private val topRatedMovie = movieRepository.getMovieTopRatedHome()
    private val nowPlayingMovie = movieRepository.getMovieNowPlayingHome()

    operator fun invoke(): Flow<List<SeeMore<List<ItemResponse>>>> = combine(
        popularMovie,
        upcomingMovie,
        topRatedMovie,
        nowPlayingMovie
    ) { popular, upcoming, topRated, nowPlaying ->

        val popularSeeMore = SeeMore("Popular", popular)
        val upcomingSeeMore = SeeMore("Upcoming", upcoming)
        val topRatedSeeMore = SeeMore("Top Rated", topRated)
        val nowPlayingSeeMore = SeeMore("Now Playing", nowPlaying)

        listOf(
            popularSeeMore,
            upcomingSeeMore,
            topRatedSeeMore,
            nowPlayingSeeMore
        )

    }
}