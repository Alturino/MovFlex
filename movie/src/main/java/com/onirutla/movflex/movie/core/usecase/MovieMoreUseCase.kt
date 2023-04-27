package com.onirutla.movflex.movie.core.usecase

import androidx.paging.PagingData
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.MovieType
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_CASTS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_NOW_PLAYING
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_POPULAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_RECOMMENDATIONS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_REVIEWS
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_SIMILAR
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_TOP_RATED
import com.onirutla.movflex.movie.domain.model.MovieType.MOVIE_UPCOMING
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieMoreUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    operator fun invoke(movieType: MovieType, movieId: Int): Flow<PagingData<Any>> =
        when (movieType) {
            MOVIE_UPCOMING -> movieRepository.getMovieUpcomingPaging() as Flow<PagingData<Any>>
            MOVIE_TOP_RATED -> movieRepository.getMovieTopRatedPaging() as Flow<PagingData<Any>>
            MOVIE_NOW_PLAYING -> movieRepository.getMovieNowPlayingPaging() as Flow<PagingData<Any>>
            MOVIE_POPULAR -> movieRepository.getMoviePopularPaging() as Flow<PagingData<Any>>
            MOVIE_RECOMMENDATIONS -> movieRepository.getMovieRecommendationsPaging(movieId) as Flow<PagingData<Any>>
            MOVIE_SIMILAR -> movieRepository.getMovieSimilarPaging(movieId) as Flow<PagingData<Any>>
            MOVIE_REVIEWS -> movieRepository.getMovieReviewsPaging(movieId) as Flow<PagingData<Any>>
            MOVIE_CASTS -> movieRepository.getMovieCastsPaging(movieId) as Flow<PagingData<Any>>
        }
}
