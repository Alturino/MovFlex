package com.onirutla.movflex.movie.core.usecase

import androidx.paging.PagingData
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_NOW_PLAYING
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_POPULAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_RECOMMENDATIONS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_SIMILAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_TOP_RATED
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_UPCOMING
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSeeMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    operator fun invoke(movieType: MovieType, movieId: Int = 0): Flow<PagingData<Movie>> =
        when (movieType) {
            MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging()
            MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging()
            MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging()
            MOVIE_POPULAR -> movieRepository.getMoviePopularPaging()
            MOVIE_RECOMMENDATIONS -> movieRepository.getMovieRecommendationsPaging(movieId)
            MOVIE_SIMILAR -> movieRepository.getMovieSimilarPaging(movieId)
        }
}
