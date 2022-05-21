package com.onirutla.movflex.data.repository.favorite

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override fun getFavorite(): Flow<PagingData<FavoriteEntity>> = Pager(
        config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { favoriteDao.getFavorite() }
    ).flow

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.deleteFavorite(favoriteEntity)

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.insertFavorite(favoriteEntity)
}