package com.onirutla.movflex.movie.core.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val dao: MovieDao,
) {
    fun observeFavoriteState(movieId: Int): Flow<Boolean> = dao.observeFavoriteState(movieId)
        .map { it != null }

    suspend fun isInDb(movieId: Int) = dao.isInDb(movieId)

    suspend fun addToFavorite(vararg movie: MovieEntity) {
        dao.addToFavorite(*movie)
    }

    suspend fun deleteFavorite(vararg movie: MovieEntity) = dao.deleteFavorite(*movie)

    fun getFavorite(): Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            dao.getFavorite()
        }
    ).flow
        .filterNotNull()

}
