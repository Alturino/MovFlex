package com.onirutla.movflex.data.repository.favorite

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.domain.repository.FavoriteRepository
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import com.onirutla.movflex.util.toContent
import com.onirutla.movflex.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override fun getFavorite(): Flow<PagingData<Content>> = Pager(
        config = PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { favoriteDao.getFavorite() }
    ).flow.map { pagingData ->
        pagingData.map {
            it.toContent()
        }
    }

    override suspend fun setFavorite(content: Content) {
        val isInDb = favoriteDao.isFavorite(content.id)
        if (isInDb != null && isInDb.isFavorite)
            favoriteDao.insertFavorite(isInDb.copy(isFavorite = false))
        else
            favoriteDao.insertFavorite(content.toEntity().copy(isFavorite = true))
    }
}