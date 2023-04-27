package com.onirutla.movflex.movie.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    repository: MovieRepository,
) {

    val movies: Flow<List<SeeMore<List<Movie>>>> = flow {
        val result = supervisorScope {
            val popular = async { repository.getMoviePopular() }
            val upcoming = async { repository.getMovieUpcoming() }
            val topRated = async { repository.getMovieTopRated() }
            val nowPlaying = async { repository.getMovieNowPlaying() }

            val popularSeeMore = SeeMore(MovieType.MOVIE_POPULAR.value, popular.await())
            val upcomingSeeMore = SeeMore(MovieType.MOVIE_UPCOMING.value, upcoming.await())
            val topRatedSeeMore = SeeMore(MovieType.MOVIE_TOP_RATED.value, topRated.await())
            val nowPlayingSeeMore =
                SeeMore(MovieType.MOVIE_NOW_PLAYING.value, nowPlaying.await())

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
