package com.onirutla.movflex.movie.core.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.remote.PagingDataSource
import com.onirutla.movflex.core.data.source.local.entities.toContent
import com.onirutla.movflex.core.domain.repository.FavoriteRepository
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.movie.domain.remote.MovieRemoteDataSource
import com.onirutla.movflex.movie.domain.repository.MovieRepository
import com.onirutla.movflex.movie.domain.toMovieDetail
import com.onirutla.movflex.movie.domain.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val favoriteRepository: FavoriteRepository,
) : MovieRepository {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    override fun getMoviePopularPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMoviePopular(position).map { it.toMovie() }
            }
        },
    ).flow

    override fun getMoviePopularHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMoviePopular().map { it.toMovie() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }

    override fun getMovieNowPlayingPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieNowPlaying(position).map { it.toMovie() }
            }
        },
    ).flow

    override fun getMovieNowPlayingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieNowPlaying().map { it.toMovie() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNotNull()

    override fun getMovieTopRatedPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieTopRated(position).map { it.toMovie() }
            }
        }
    ).flow

    override fun getMovieTopRatedHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieTopRated().map { it.toMovie() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
        emit(emptyList())
    }


    override fun getMovieUpcomingPaging(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            PagingDataSource { position ->
                remoteDataSource.getMovieUpcoming(position).map { it.toMovie() }
            }
        }
    ).flow

    override fun getMovieUpcomingHome(): Flow<List<Content>> = flow {
        val dto = remoteDataSource.getMovieUpcoming().map { it.toMovie() }
        emit(dto)
    }.catch {
        Log.d(TAG, "$it")
    }.filterNotNull()
        .filterNot { it.isEmpty() }

    override fun getMovieDetail(id: Int): Flow<Content> = flow {
        val isInDb = favoriteRepository.isFavorite(id)
        if (isInDb != null)
            emit(isInDb.toContent())
        else {
            val response = remoteDataSource.getMovieDetail(id)
            emit(response?.toMovieDetail())
        }
    }.filterNotNull()

}
