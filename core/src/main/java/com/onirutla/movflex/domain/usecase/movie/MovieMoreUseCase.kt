package com.onirutla.movflex.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.type.MovieType
import javax.inject.Inject

class MovieMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(movieType: MovieType): LiveData<PagingData<Content>> =
        when (movieType) {
            MovieType.MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging().asLiveData()
            MovieType.MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging().asLiveData()
            MovieType.MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging().asLiveData()
            MovieType.MOVIE_POPULAR -> movieRepository.getMoviePopularPaging().asLiveData()
        }

}