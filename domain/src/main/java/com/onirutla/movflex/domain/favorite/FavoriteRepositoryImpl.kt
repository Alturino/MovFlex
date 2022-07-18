package com.onirutla.movflex.domain.favorite

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.source.local.dao.FavoriteDao
import com.onirutla.movflex.source.local.entities.FavoriteEntity
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ViewModelScoped
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override fun getFavorite(): Flow<PagingData<FavoriteEntity>> = Pager(
        config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { favoriteDao.getFavorite() }
    ).flow

    override suspend fun setFavorite(movie: FavoriteEntity) {
        favoriteDao.isFavorite(movie.id).collect {
            if (it == null || movie.isFavorite)
                favoriteDao.insertFavorite(movie.copy(isFavorite = false))
            else
                favoriteDao.insertFavorite(it.copy(isFavorite = true))
        }
    }
}