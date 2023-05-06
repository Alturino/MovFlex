package com.onirutla.movflex.movie.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.core.data.source.remote.response.util.toCasts
import com.onirutla.movflex.core.data.source.remote.response.util.toReviews
import com.onirutla.movflex.movie.core.local.MovieLocalDataSource
import com.onirutla.movflex.movie.core.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.movie.util.toDetail
import com.onirutla.movflex.movie.util.toEntity
import com.onirutla.movflex.movie.util.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
                getMoviePopular(position)
            }
        },
    ).flow

    suspend fun getMoviePopular(page: Int = 1): List<Movie> = remote.getMoviePopular(page)
        .toMovie()

    fun getMovieNowPlayingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieNowPlaying(position)
            }
        },
    ).flow

    suspend fun getMovieNowPlaying(page: Int = 1): List<Movie> = remote.getMovieNowPlaying(page)
        .toMovie()

    fun getMovieTopRatedPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieTopRated(position)
            }
        }
    ).flow

    suspend fun getMovieTopRated(page: Int = 1): List<Movie> = remote.getMovieTopRated(page)
        .toMovie()

    fun getMovieUpcomingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieUpcoming(position).toMovie()
            }
        }
    ).flow

    suspend fun getMovieUpcoming(): List<Movie> = remote.getMovieUpcoming()
        .toMovie()

    fun getMovieDetail(id: Int): Flow<MovieDetail> = flow {
        val isInDb = local.isInDb(id)
        val dto = isInDb?.toDetail() ?: remote.getMovieDetail(id)?.toDetail()
        emit(dto)
    }.catch {
        Timber.d(it)
    }.filterNotNull()

    suspend fun setFavorite(movie: MovieDetail) {
        val isFavorite = local.isInDb(movie.id)
        if (isFavorite == null)
            local.addToFavorite(movie.toEntity())
        else
            local.deleteFavorite(isFavorite)
    }

    fun getMovieFavorite(): Flow<PagingData<Movie>> = local.getFavorite()
        .map { pagingData ->
            pagingData.map {
                it.toMovie()
            }
        }

    suspend fun getMovieSimilar(movieId: Int, page: Int = 1): List<Movie> =
        remote.getMovieSimilar(movieId, page).toMovie()

    fun getMovieSimilarPaging(movieId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieSimilar(movieId, position)
            }
        }
    ).flow

    suspend fun getMovieRecommendations(movieId: Int, page: Int = 1): List<Movie> =
        remote.getMovieRecommendations(movieId, page).toMovie()


    fun getMovieRecommendationsPaging(movieId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieRecommendations(movieId, position)
            }
        }
    ).flow

    suspend fun getMovieReviews(movieId: Int, page: Int = 1): List<Review> =
        remote.getMovieReviews(movieId, page).toReviews()


    fun getMovieReviewsPaging(movieId: Int): Flow<PagingData<Review>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieReviews(movieId, position)
            }
        }
    ).flow

    suspend fun getMovieCasts(movieId: Int, page: Int = 1): List<Cast> =
        remote.getMovieCasts(movieId, page).toCasts()

    fun getMovieCastsPaging(movieId: Int): Flow<PagingData<Cast>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PagingDataSource { position ->
                getMovieCasts(movieId, position)
            }
        }
    ).flow


    fun observeFavoriteState(movieId: Int): Flow<Boolean> = local.observeFavoriteState(movieId)

}
