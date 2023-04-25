package com.onirutla.movflex.movie.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.core.util.toCasts
import com.onirutla.movflex.core.util.toReviews
import com.onirutla.movflex.movie.core.local.MovieLocalDataSource
import com.onirutla.movflex.movie.core.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.movie.util.toDetail
import com.onirutla.movflex.movie.util.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
) {

    fun getMoviePopularPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMoviePopular(position).toDomain()
            }
        },
    ).flow

    suspend fun getMoviePopularHome(): List<Movie> = remote.getMoviePopular()
        .toDomain()

    fun getMovieNowPlayingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieNowPlaying(position).toDomain()
            }
        },
    ).flow

    suspend fun getMovieNowPlayingHome(): List<Movie> = remote.getMovieNowPlaying()
        .toDomain()

    fun getMovieTopRatedPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieTopRated(position).toDomain()
            }
        }
    ).flow

    suspend fun getMovieTopRatedHome(): List<Movie> = remote.getMovieTopRated()
        .toDomain()

    fun getMovieUpcomingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieUpcoming(position).toDomain()
            }
        }
    ).flow

    suspend fun getMovieUpcomingHome(): List<Movie> = remote.getMovieUpcoming()
        .toDomain()

    fun getMovieDetail(id: Int): Flow<MovieDetail> = flow {
        val dto = remote.getMovieDetail(id)?.toDetail()
        emit(dto)
    }.catch {
        Timber.d(it)
    }.filterNotNull()

    suspend fun setFavorite(movie: MovieDetail) {
        val isFavorite = local.isFavorite(movie.id)
        if (isFavorite == null)
            local.addToFavorite(movie)
        else
            local.deleteFavorite(isFavorite)
    }

    fun getMovieFavorite(): Flow<PagingData<Movie>> = local.getFavorite()

    fun getMovieSimilar(movieId: Int): Flow<List<Movie>> = flow {
        val result = remote.getMovieSimilar(movieId).toDomain()
        emit(result)
    }

    fun getMovieSimilarPaging(movieId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieSimilar(movieId = movieId, page = position).toDomain()
            }
        }
    ).flow

    fun getMovieRecommendations(movieId: Int): Flow<List<Movie>> = flow {
        val result = remote.getMovieRecommendations(movieId).toDomain()
        emit(result)
    }

    fun getMovieRecommendationsPaging(movieId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieRecommendations(movieId, page = position).toDomain()
            }
        }
    ).flow

    fun getMovieReviews(movieId: Int): Flow<List<Review>> = flow {
        val result = remote.getMovieReviews(movieId).toReviews()
        emit(result)
    }

    fun getMovieReviewsPaging(movieId: Int): Flow<PagingData<Review>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieReviews(movieId = movieId, page = position).toReviews()
            }
        }
    ).flow

    fun getMovieCasts(movieId: Int): Flow<List<Cast>> = flow {
        val result = remote.getMovieCasts(movieId).toCasts()
        emit(result)
    }

    fun observeFavoriteState(movieId: Int): Flow<Boolean> = local.observeFavoriteState(movieId)

}
