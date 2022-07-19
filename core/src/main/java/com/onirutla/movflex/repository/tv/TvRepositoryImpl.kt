package com.onirutla.movflex.repository.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.source.PagingDataSource
import com.onirutla.movflex.source.local.dao.FavoriteDao
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import com.onirutla.movflex.source.remote.response.tv.toEntity
import com.onirutla.movflex.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import com.onirutla.movflex.model.Content
import com.onirutla.movflex.util.mapper
import com.onirutla.movflex.util.toContent
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

    override fun getTvPopularPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvPopular(position).mapper { it.toContent() }
            }
        },
    ).liveData

    override fun getTvPopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvPopular().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvOnTheAirPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvOnTheAir(position).mapper { it.toContent() }
            }
        },
    ).liveData

    override fun getTvOnTheAirHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvOnTheAir().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvTopRatedPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvTopRated(position).mapper { it.toContent() }
            }
        }
    ).liveData

    override fun getTvTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvTopRated().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvAiringTodayPaging(): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvAiringToday(position).mapper { it.toContent() }
            }
        }
    ).liveData

    override fun getTvAiringTodayHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvAiringToday().mapper { it.toContent() }
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
