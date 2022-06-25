package com.onirutla.movflex.data.repository.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.data.source.PagingDataSource
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.data.source.remote.response.tv.toEntity
import com.onirutla.movflex.data.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TvRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvRemoteDataSource,
    private val favoriteDao: FavoriteDao
) : TvRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getTvPopularPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvPopular(
                    position
                )
            }
        },
    ).liveData

    override fun getTvPopularHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getTvPopular()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvOnTheAirPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvOnTheAir(
                    position
                )
            }
        },
    ).liveData

    override fun getTvOnTheAirHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getTvOnTheAir()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvTopRatedPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvTopRated(
                    position
                )
            }
        }
    ).liveData

    override fun getTvTopRatedHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getTvTopRated()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvAiringTodayPaging(): LiveData<PagingData<ItemDto>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvAiringToday(
                    position
                )
            }
        }
    ).liveData

    override fun getTvAiringTodayHome(): Flow<List<ItemDto>> = flow {
        val dto = remoteDataSource.getTvAiringToday()
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvDetail(id: Int): Flow<FavoriteEntity> =
        favoriteDao.isFavorite(id).map {
            if (it != null) {
                it
            } else {
                val response = remoteDataSource.getTvDetail(id)
                response.toEntity()
            }
        }.catch {
            Log.d(TAG, "$it")
        }
}
