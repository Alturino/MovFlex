package com.onirutla.movflex.domain.repository.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.source.PagingDataSource
import com.onirutla.movflex.source.local.dao.FavoriteDao
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import com.onirutla.movflex.source.remote.movie.MovieRemoteDataSource
import com.onirutla.movflex.source.remote.response.movie.toEntity
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.util.mapper
import com.onirutla.movflex.domain.util.toContent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoriteDao: FavoriteDao
) : MovieRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getMoviePopularPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMoviePopular(position).mapper { it.toContent() }
            }
        },
    ).liveData

    override fun getMoviePopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMoviePopular().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieNowPlaying(position).mapper { it.toContent() }
            }
        },
    ).liveData

    override fun getMovieNowPlayingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieNowPlaying().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieTopRatedPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieTopRated(position).mapper { it.toContent() }
            }
        }
    ).liveData

    override fun getMovieTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieTopRated().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }


    override fun getMovieUpcomingPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieUpcoming(position).mapper { it.toContent() }
            }
        }
    ).liveData

    override fun getMovieUpcomingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieUpcoming().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieDetail(id: Int): Flow<FavoriteEntity> = favoriteDao.isFavorite(id).map {
        if (it != null)
            it
        else {
            val response = remoteDataSource.getMovieDetail(id)
            response.toEntity()
        }
    }.catch {
        Log.d(TAG, "$it")
        emit(FavoriteEntity())
    }
}