package com.onirutla.movflex.data.repository.favorite

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onirutla.movflex.data.source.local.dao.FavoriteDao
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.util.Constants.PAGE_SIZE
import com.onirutla.movflex.util.toContent
import com.onirutla.movflex.util.toEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
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
        favoriteDao.isFavorite(content.id).collect {
            if (it == null || content.isFavorite)
                favoriteDao.insertFavorite(content.copy(isFavorite = false).toEntity())
            else
                favoriteDao.insertFavorite(it.copy(isFavorite = true))
        }
    }
}