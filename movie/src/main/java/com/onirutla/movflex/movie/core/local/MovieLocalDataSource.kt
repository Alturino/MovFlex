package com.onirutla.movflex.movie.core.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onirutla.movflex.core.data.source.local.dao.MovieDao
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail
import com.onirutla.movflex.movie.util.toDomain
import com.onirutla.movflex.movie.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val dao: MovieDao,
) {
    fun observeFavoriteState(movieId: Int): Flow<Boolean> =
        dao.observeFavoriteState(movieId).map { it != null }

    fun isFavorite(movieId: Int) = dao.isFavorite(movieId)

    suspend fun addToFavorite(vararg movie: MovieDetail) {
        val entity = movie.toList()
            .map { it.toEntity() }
            .toTypedArray()

        dao.addToFavorite(*entity)
    }

    suspend fun deleteFavorite(vararg movie: MovieEntity) = dao.deleteFavorite(*movie)

    fun getFavorite(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            dao.getFavorite()
        }
    ).flow
        .filterNotNull()
        .map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
}
