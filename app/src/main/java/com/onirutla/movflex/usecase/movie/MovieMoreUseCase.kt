package com.onirutla.movflex.usecase.movie

import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.util.Constants.TITLE_MOVIE_NOW_PLAYING
import com.onirutla.movflex.util.Constants.TITLE_MOVIE_TOP_RATED
import com.onirutla.movflex.util.Constants.TITLE_MOVIE_UPCOMING
import com.onirutla.movflex.util.Constants.TITLE_POPULAR
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(category: String) = when (category) {
        TITLE_MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging()
        TITLE_MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging()
        TITLE_POPULAR -> movieRepository.getMoviePopularPaging()
        TITLE_MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging()
        else -> flow { }
    }


}