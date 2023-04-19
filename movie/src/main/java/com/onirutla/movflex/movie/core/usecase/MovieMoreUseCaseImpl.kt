package com.onirutla.movflex.movie.core.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.movie.domain.usecase.MovieMoreUseCase
import com.onirutla.movflex.movie.domain.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.domain.model.MovieType.*
import javax.inject.Inject

class MovieMoreUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : MovieMoreUseCase {

    override fun invoke(movieType: MovieType): LiveData<PagingData<Content>> =
        when (movieType) {
            MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging().asLiveData()
            MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging().asLiveData()
            MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging().asLiveData()
            MOVIE_POPULAR -> movieRepository.getMoviePopularPaging().asLiveData()
        }
}
