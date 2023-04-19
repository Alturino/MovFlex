package com.onirutla.movflex.tv.core.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.data.source.local.entities.toContent
import com.onirutla.movflex.core.domain.repository.FavoriteRepository
import com.onirutla.movflex.core.util.Constants
import com.onirutla.movflex.tv.domain.remote.TvRemoteDataSource
import com.onirutla.movflex.tv.domain.repository.TvRepository
import com.onirutla.movflex.tv.domain.util.toContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvRemoteDataSource,
    private val favoriteRepository: FavoriteRepository,
) : TvRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getTvPopularPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvPopular(position).map { it.toContent() }
            }
        },
    ).flow

    override fun getTvPopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvPopular().map { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNot { it.isEmpty() }

    override fun getTvOnTheAirPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvOnTheAir(position).map { it.toContent() }
            }
        },
    ).flow

    override fun getTvOnTheAirHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvOnTheAir().map { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvTopRatedPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvTopRated(position).map { it.toContent() }
            }
        }
    ).flow

    override fun getTvTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvTopRated().map { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getTvAiringTodayPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvAiringToday(position).map { it.toContent() }
            }
        }
    ).flow

    override fun getTvAiringTodayHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getTvAiringToday().map { it.toContent() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNot { it.isEmpty() }

    override fun getTvDetail(id: Int): Flow<Content> = flow {
        val isInDb = favoriteRepository.isFavorite(id)
        emit(isInDb?.toContent())
    }.catch {
        Log.d(TAG, "$it")
    }.filterNotNull()
}
