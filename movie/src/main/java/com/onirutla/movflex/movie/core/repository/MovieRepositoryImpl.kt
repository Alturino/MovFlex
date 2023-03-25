package com.onirutla.movflex.movie.core.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.PagingDataSource
import com.onirutla.movflex.core.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.core.data.source.local.entities.toContent
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.movie.domain.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.repository.MovieRepository
import com.onirutla.movflex.movie.domain.toContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoriteDao: FavoriteDao,
) : MovieRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getMoviePopularPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMoviePopular(position).map { it.toContent() }
            }
        },
    ).flow

    override fun getMoviePopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMoviePopular().map { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieNowPlaying(position).map { it.toContent() }
            }
        },
    ).flow

    override fun getMovieNowPlayingHome(): Flow<List<Content>> =
        flow {
            val dto = remoteDataSource.getMovieNowPlaying().map { it.toContent() }
            emit(dto)
        }.catch {
            Log.d(TAG, "$it")
            emit(emptyList())
        }

    override fun getMovieTopRatedPaging(): Flow<PagingData<Content>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingDataSource { position ->
                    remoteDataSource.getMovieTopRated(position).map { it.toContent() }
                }
            }
        ).flow

    override fun getMovieTopRatedHome(): Flow<List<Content>> =
        flow {
            val dto = remoteDataSource.getMovieTopRated().map { it.toContent() }
            emit(dto)
        }.catch {
            Log.d(TAG, "$it")
            emit(emptyList())
        }


    override fun getMovieUpcomingPaging(): Flow<PagingData<Content>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingDataSource { position ->
                    remoteDataSource.getMovieUpcoming(position).map { it.toContent() }
                }
            }
        ).flow

    override fun getMovieUpcomingHome(): Flow<List<Content>> =
        flow {
            val dto = remoteDataSource.getMovieUpcoming().map { it.toContent() }
            emit(dto)
        }.catch {
            Log.d(TAG, "$it")
            emit(emptyList())
        }

    override fun getMovieDetail(id: Int): Flow<Content> = flow {
        val isInDb = favoriteDao.isFavorite(id)
        if (isInDb != null)
            emit(isInDb.toContent())
        else {
            val response = remoteDataSource.getMovieDetail(id)
            emit(response.toContent())
        }
    }

}
