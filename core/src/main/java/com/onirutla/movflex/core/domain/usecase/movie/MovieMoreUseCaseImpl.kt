package com.onirutla.movflex.core.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.type.MovieType
import com.onirutla.movflex.core.domain.repository.MovieRepository
import javax.inject.Inject

class MovieMoreUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieMoreUseCase {

    override fun invoke(movieType: MovieType): LiveData<PagingData<Content>> =
        when (movieType) {
            MovieType.MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging().asLiveData()
            MovieType.MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging().asLiveData()
            MovieType.MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging().asLiveData()
            MovieType.MOVIE_POPULAR -> movieRepository.getMoviePopularPaging().asLiveData()
        }
}