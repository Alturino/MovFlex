package com.onirutla.movflex.movie.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    repository: MovieRepository,
) {

    val movies: Flow<List<SeeMore<List<Movie>>>> = flow {
        val result = supervisorScope {
            val popular = async { repository.getMoviePopularHome() }
            val upcoming = async { repository.getMovieUpcomingHome() }
            val topRated = async { repository.getMovieTopRatedHome() }
            val nowPlaying = async { repository.getMovieNowPlayingHome() }

            val popularSeeMore = SeeMore("Popular", popular.await())
            val upcomingSeeMore = SeeMore("Upcoming", upcoming.await())
            val topRatedSeeMore = SeeMore("Top Rated", topRated.await())
            val nowPlayingSeeMore = SeeMore("Now Playing", nowPlaying.await())

            listOf(
                popularSeeMore,
                upcomingSeeMore,
                topRatedSeeMore,
                nowPlayingSeeMore
            )
        }
        emit(result)
    }
}
