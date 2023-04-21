package com.onirutla.movflex.tv.core.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.util.Constants
import com.onirutla.movflex.tv.core.remote.TvRemoteDataSource
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail
import com.onirutla.movflex.tv.domain.util.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val remoteDataSource: TvRemoteDataSource,
) {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    fun getTvPopularPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvPopular(position).map { it.toDomain() }
            }
        },
    ).flow

    suspend fun getTvPopularHome(): List<Tv> = remoteDataSource.getTvPopular()
        .map { it.toDomain() }

    fun getTvOnTheAirPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvOnTheAir(position).map { it.toDomain() }
            }
        },
    ).flow

    suspend fun getTvOnTheAirHome(): List<Tv> = remoteDataSource.getTvOnTheAir()
        .map { it.toDomain() }

    fun getTvTopRatedPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvTopRated(position).map { it.toDomain() }
            }
        }
    ).flow

    suspend fun getTvTopRatedHome(): List<Tv> = remoteDataSource.getTvTopRated()
        .map { it.toDomain() }

    fun getTvAiringTodayPaging(): Flow<PagingData<Tv>> = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getTvAiringToday(position).map { it.toDomain() }
            }
        }
    ).flow

    suspend fun getTvAiringTodayHome(): List<Tv> = remoteDataSource.getTvAiringToday()
        .map { it.toDomain() }

    fun getTvDetail(id: Int): Flow<TvDetail> = flow {
        val tvDetail = remoteDataSource.getTvDetail(id)?.toDomain() ?: TvDetail()
        emit(tvDetail)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNotNull()
}
