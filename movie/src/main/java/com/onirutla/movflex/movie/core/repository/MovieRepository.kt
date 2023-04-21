package com.onirutla.movflex.movie.core.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.movie.core.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.movie.util.toDomain
import com.onirutla.movflex.movie.util.toEntity
import com.onirutla.movflex.movie.util.toMovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: MovieRemoteDataSource,
    private val dao: MovieDao,
) {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    fun getMoviePopularPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMoviePopular(position).map { it.toDomain() }
            }
        },
    ).flow

    suspend fun getMoviePopularHome(): List<Movie> = remote.getMoviePopular()
        .map { it.toDomain() }

    fun getMovieNowPlayingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieNowPlaying(position).map { it.toDomain() }
            }
        },
    ).flow

    suspend fun getMovieNowPlayingHome(): List<Movie> = remote.getMovieNowPlaying()
        .map { it.toDomain() }

    fun getMovieTopRatedPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieTopRated(position).map { it.toDomain() }
            }
        }
    ).flow

    suspend fun getMovieTopRatedHome(): List<Movie> = remote.getMovieTopRated()
        .map { it.toDomain() }

    fun getMovieUpcomingPaging(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remote.getMovieUpcoming(position).map { it.toDomain() }
            }
        }
    ).flow

    suspend fun getMovieUpcomingHome(): List<Movie> = remote.getMovieUpcoming()
        .map { it.toDomain() }

    fun getMovieDetail(id: Int): Flow<MovieDetail> = flow {
        val dto = remote.getMovieDetail(id)?.toMovieDetail()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNotNull()

    suspend fun setFavorite(movie: MovieDetail) {
        val isFavorite = dao.isFavorite(movie.id)
        if (isFavorite == null)
            dao.addToFavorite(movie.toEntity())
        else
            dao.deleteFavorite(isFavorite)
    }

}
