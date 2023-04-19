package com.onirutla.movflex.movie.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.domain.repository.MovieRepository
import com.onirutla.movflex.movie.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    movieRepository: MovieRepository,
) : MovieUseCase {

    private val popularMovie = movieRepository.getMoviePopularHome()
    private val upcomingMovie = movieRepository.getMovieUpcomingHome()
    private val topRatedMovie = movieRepository.getMovieTopRatedHome()
    private val nowPlayingMovie = movieRepository.getMovieNowPlayingHome()

    override val movies: Flow<List<SeeMore<List<Content>>>> = combine(
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
