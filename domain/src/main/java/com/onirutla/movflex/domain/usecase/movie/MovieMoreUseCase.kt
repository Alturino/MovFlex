package com.onirutla.movflex.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.domain.repository.movie.MovieRepository
import com.onirutla.movflex.domain.model.MovieType
import com.onirutla.movflex.domain.model.Content
import javax.inject.Inject

class MovieMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(movieType: MovieType): LiveData<PagingData<Content>> =
        when (movieType) {
            MovieType.MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging()
            MovieType.MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging()
            MovieType.MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging()
            MovieType.MOVIE_POPULAR -> movieRepository.getMoviePopularPaging()
        }

}