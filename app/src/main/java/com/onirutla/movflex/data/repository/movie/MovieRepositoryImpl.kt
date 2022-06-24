package com.onirutla.movflex.data.repository.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.PagingDataSource
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.movie.toEntity
import com.onirutla.movflex.data.source.remote.service.MovieApiService
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val favoriteDao: FavoriteDao
) : MovieRepository {

    override fun getMoviePopularPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> movieApiService.getMoviePopular(position) }
        },
    ).liveData

    override fun getMoviePopularHome(): Flow<List<ItemResponse>> = flow {
        val response = movieApiService.getMoviePopular()
        if (response.isSuccessful)
            emit(response.body()!!.results)
        else
            emit(emptyList())
    }.catch {
        Log.d(this@MovieRepositoryImpl.javaClass.simpleName, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> movieApiService.getMovieNowPlaying(position) }
        },
    ).liveData

    override fun getMovieNowPlayingHome(): Flow<List<ItemResponse>> = flow {
        val response = movieApiService.getMovieNowPlaying()
        if (response.isSuccessful)
            emit(response.body()!!.results)
        else
            emit(emptyList())
    }.catch {
        Log.d(this@MovieRepositoryImpl.javaClass.simpleName, "$it")
        emit(emptyList())
    }

    override fun getMovieTopRatedPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> movieApiService.getMovieTopRated(position) }
        }
    ).liveData

    override fun getMovieTopRatedHome(): Flow<List<ItemResponse>> = flow {
        val response = movieApiService.getMovieTopRated()
        if (response.isSuccessful)
            emit(response.body()!!.results)
        else
            emit(emptyList())
    }.catch {
        Log.d(this@MovieRepositoryImpl.javaClass.simpleName, "$it")
        emit(emptyList())
    }


    override fun getMovieUpcomingPaging(): LiveData<PagingData<ItemResponse>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position -> movieApiService.getMovieUpcoming(position) }
        }
    ).liveData

    override fun getMovieUpcomingHome(): Flow<List<ItemResponse>> = flow {
        val response = movieApiService.getMovieUpcoming()
        if (response.isSuccessful)
            emit(response.body()!!.results)
        else
            emit(emptyList())
    }.catch {
        Log.d(this@MovieRepositoryImpl.javaClass.simpleName, "$it")
        emit(emptyList())
    }


    override fun getMovieDetail(id: Int): Flow<FavoriteEntity> =
        favoriteDao.isFavorite(id).map {
            if (it != null) {
                it
            } else {
                val response = movieApiService.getMovieDetail(id)
                if (response.isSuccessful) {
                    favoriteDao.insertFavorite(response.body()!!.toEntity())
                    response.body()!!.toEntity()
                } else {
                    FavoriteEntity()
                }
            }
        }.catch {
            Log.d(this@MovieRepositoryImpl.javaClass.simpleName, "$it")
            emit(FavoriteEntity())
        }
}