package com.onirutla.movflex.data.repository.movie

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.PagingDataSource
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.remote.movie.MovieRemoteDataSource
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import com.onirutla.movflex.util.mapper
import com.onirutla.movflex.util.toContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoriteDao: FavoriteDao
) : MovieRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getMoviePopularPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMoviePopular(position).mapper { it.toContent() }
            }
        },
    ).flow

    override fun getMoviePopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMoviePopular().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieNowPlaying(position).mapper { it.toContent() }
            }
        },
    ).flow

    override fun getMovieNowPlayingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieNowPlaying().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieTopRatedPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieTopRated(position).mapper { it.toContent() }
            }
        }
    ).flow

    override fun getMovieTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieTopRated().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }


    override fun getMovieUpcomingPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieUpcoming(position).mapper { it.toContent() }
            }
        }
    ).flow

    override fun getMovieUpcomingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieUpcoming().mapper { it.toContent() }
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