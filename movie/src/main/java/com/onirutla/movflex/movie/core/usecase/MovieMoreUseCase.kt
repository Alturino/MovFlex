package com.onirutla.movflex.movie.core.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_NOW_PLAYING
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_POPULAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_TOP_RATED
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_UPCOMING
import javax.inject.Inject

class MovieMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    fun invoke(movieType: MovieType): LiveData<PagingData<Movie>> =
        when (movieType) {
            MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging().asLiveData()
            MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging().asLiveData()
            MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging().asLiveData()
            MOVIE_POPULAR -> movieRepository.getMoviePopularPaging().asLiveData()
        }
}
