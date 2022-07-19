package com.onirutla.movflex.data.repository.tv

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.PagingDataSource
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.remote.tv.TvRemoteDataSource
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.util.Constants.PAGE_SIZE
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

    override fun getTvPopularPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvPopular(position).mapper { it.toContent() }
            }
        },
    ).flow

    override fun getTvPopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvPopular().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvOnTheAirPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvOnTheAir(position).mapper { it.toContent() }
            }
        },
    ).flow

    override fun getTvOnTheAirHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvOnTheAir().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvTopRatedPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvTopRated(position).mapper { it.toContent() }
            }
        }
    ).flow

    override fun getTvTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvTopRated().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvAiringTodayPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvAiringToday(position).mapper { it.toContent() }
            }
        }
    ).flow

    override fun getTvAiringTodayHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvAiringToday().mapper { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvDetail(id: Int): Flow<Content> =
        favoriteDao.isFavorite(id).map {
            if (it != null) {
                it.toContent()
            } else {
                val response = remoteDataSource.getTvDetail(id)
                response.toContent()
            }
        }.catch {
            Log.d(TAG, "$it")
        }
}
