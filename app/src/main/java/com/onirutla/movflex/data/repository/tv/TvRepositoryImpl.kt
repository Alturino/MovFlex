package com.onirutla.movflex.data.repository.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.local.entities.ItemType
import com.onirutla.movflex.data.source.remote.PagingDataSource
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.tv.toEntity
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
    private val tvApiService: TvApiService,
    private val favoriteDao: FavoriteDao
) : TvRepository {

    override fun getTvPopularPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvPopular(position) } },
    ).liveData

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

    override fun getTvOnTheAirPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvOnTheAir(position) } },
    ).liveData

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

    override fun getTvTopRatedPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource { position -> tvApiService.getTvTopRated(position) } }
    ).liveData

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

    override fun getTvAiringTodayPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                tvApiService.getTvAiringToday(
                    position
                )
            }
        }
    ).liveData

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

    override fun getTvDetail(id: Int): Flow<FavoriteEntity> = flow {
        try {
            val fromDb = favoriteDao.isFavorite(id)
            if (fromDb != null) {
                emit(fromDb)
            } else {
                val response = tvApiService.getTvDetail(id)
                if (response.isSuccessful)
                    emit(response.body()!!.toEntity())
                else
                    emit(FavoriteEntity(type = ItemType.Tv))
            }
        } catch (ioException: IOException) {
            Log.d("MovieRepo", "$ioException")
            emit(FavoriteEntity())
        } catch (httpException: HttpException) {
            Log.d("MovieRepo", "$httpException")
            emit(FavoriteEntity())
        }
    }

    override suspend fun setFavorite(tv: FavoriteEntity) {
        val fromDb = tv.id.let { favoriteDao.isFavorite(it) }
        if (fromDb != null && fromDb.isFavorite)
            favoriteDao.insertFavorite(tv.copy(isFavorite = false))
        else
            favoriteDao.insertFavorite(tv.copy(isFavorite = true))
    }
}
