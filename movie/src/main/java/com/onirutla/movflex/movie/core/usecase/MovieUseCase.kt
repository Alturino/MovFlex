package com.onirutla.movflex.movie.core.usecase

import android.content.Context
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    @ApplicationContext
    context: Context,
    repository: MovieRepository,
) {

    val movies: Flow<List<SeeMore<List<Movie>>>> = flow {
        val result = supervisorScope {
            val popular = async { repository.getMoviePopular() }
            val upcoming = async { repository.getMovieUpcoming() }
            val topRated = async { repository.getMovieTopRated() }
            val nowPlaying = async { repository.getMovieNowPlaying() }

            val popularSeeMore = SeeMore(
                title = context.getString(MovieType.MOVIE_POPULAR.value),
                items = popular.await()
            )
            val upcomingSeeMore = SeeMore(
                title = context.getString(MovieType.MOVIE_UPCOMING.value),
                items = upcoming.await()
            )
            val topRatedSeeMore = SeeMore(
                title = context.getString(MovieType.MOVIE_TOP_RATED.value),
                items = topRated.await()
            )
            val nowPlayingSeeMore = SeeMore(
                title = context.getString(MovieType.MOVIE_NOW_PLAYING.value),
                items = nowPlaying.await()
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
