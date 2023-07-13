package com.onirutla.movflex.movie.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    repository: MovieRepository,
) {

    val movies: Flow<List<SeeMore<List<Movie>>>> = flow {
        val result = supervisorScope {
            val popular = withContext(coroutineContext) { repository.getMoviePopular() }
            val upcoming = withContext(coroutineContext) { repository.getMovieUpcoming() }
            val topRated = withContext(coroutineContext) { repository.getMovieTopRated() }
            val nowPlaying = withContext(coroutineContext) { repository.getMovieNowPlaying() }

            val popularSeeMore = SeeMore(
                title = MovieType.MOVIE_POPULAR.value,
                items = popular
            )
            val upcomingSeeMore = SeeMore(
                title = MovieType.MOVIE_UPCOMING.value,
                items = upcoming
            )
            val topRatedSeeMore = SeeMore(
                title = MovieType.MOVIE_TOP_RATED.value,
                items = topRated
            )
            val nowPlayingSeeMore = SeeMore(
                title = MovieType.MOVIE_NOW_PLAYING.value,
                items = nowPlaying
            )

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
