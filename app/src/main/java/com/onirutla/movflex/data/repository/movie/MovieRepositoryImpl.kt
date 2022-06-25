package com.onirutla.movflex.data.repository.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.data.source.PagingDataSource
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.movie.MovieRemoteDataSource
import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.data.source.remote.response.movie.toEntity
import com.onirutla.movflex.util.Constants.PAGE_SIZE
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

    override fun getMoviePopularPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> remoteDataSource.getMoviePopular(position) }
        },
    ).liveData

    override fun getMoviePopularHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getMoviePopular()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> remoteDataSource.getMovieNowPlaying(position) }
        },
    ).liveData

    override fun getMovieNowPlayingHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getMovieNowPlaying()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieTopRatedPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> remoteDataSource.getMovieTopRated(position) }
        }
    ).liveData

    override fun getMovieTopRatedHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getMovieTopRated()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }


    override fun getMovieUpcomingPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> remoteDataSource.getMovieUpcoming(position) }
        }
    ).liveData

    override fun getMovieUpcomingHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getMovieUpcoming()
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