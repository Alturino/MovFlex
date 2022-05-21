package com.onirutla.movflex.data.repository.tv

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.remote.PagingDataSource
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.tv.TvResponseDetail
import com.onirutla.movflex.data.source.remote.service.TvApiService
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@ViewModelScoped
class TvRepositoryImpl @Inject constructor(
    private val tvApiService: TvApiService
) : TvRepository {

    override fun getTvPopularPaging(): Flow<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvPopular(position) } },
    ).flow

    override fun getTvPopularHome(): Flow<List<ItemResponse>> = flow {
        try {
            val response = tvApiService.getTvPopular()
            if (response.isSuccessful)
                emit(response.body()!!.results)
            else
                emit(emptyList())
        } catch (ioException: IOException) {
            Log.d("TvRepo", "$ioException")
            emit(emptyList())
        } catch (httpException: HttpException) {
            Log.d("TvRepo", "$httpException")
            emit(emptyList())
        }
    }

    override fun getTvOnTheAirPaging(): Flow<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvOnTheAir(position) } },
    ).flow

    override fun getTvOnTheAirHome(): Flow<List<ItemResponse>> = flow {
        try {
            val response = tvApiService.getTvOnTheAir()
            if (response.isSuccessful)
                emit(response.body()!!.results)
            else
                emit(emptyList())
        } catch (ioException: IOException) {
            Log.d("TvRepo", "$ioException")
            emit(emptyList())
        } catch (httpException: HttpException) {
            Log.d("TvRepo", "$httpException")
            emit(emptyList())
        }
    }

    override fun getTvTopRatedPaging(): Flow<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvTopRated(position) } }
    ).flow

    override fun getTvTopRatedHome(): Flow<List<ItemResponse>> = flow {
        try {
            val response = tvApiService.getTvTopRated()
            if (response.isSuccessful)
                emit(response.body()!!.results)
            else
                emit(emptyList())
        } catch (ioException: IOException) {
            Log.d("TvRepo", "$ioException")
            emit(emptyList())
        } catch (httpException: HttpException) {
            Log.d("TvRepo", "$httpException")
            emit(emptyList())
        }
    }

    override fun getTvAiringTodayPaging(): Flow<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvAiringToday(position) } }
    ).flow

    override fun getTvAiringTodayHome(): Flow<List<ItemResponse>> = flow {
        try {
            val response = tvApiService.getTvAiringToday()
            if (response.isSuccessful)
                emit(response.body()!!.results)
            else
                emit(emptyList())
        } catch (ioException: IOException) {
            Log.d("TvRepo", "$ioException")
            emit(emptyList())
        } catch (httpException: HttpException) {
            Log.d("TvRepo", "$httpException")
            emit(emptyList())
        }
    }

    override fun getTvDetail(id: Int): Flow<TvResponseDetail> = flow {
        try {
            val response = tvApiService.getTvDetail(id)
            if (response.isSuccessful)
                emit(response.body()!!)
            else
                emit(TvResponseDetail())
        } catch (ioException: IOException) {
            Log.d("MovieRepo", "$ioException")
            emit(TvResponseDetail())
        } catch (httpException: HttpException) {
            Log.d("MovieRepo", "$httpException")
            emit(TvResponseDetail())
        }
    }
}
