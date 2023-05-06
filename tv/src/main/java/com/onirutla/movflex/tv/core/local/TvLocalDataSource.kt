package com.onirutla.movflex.tv.core.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onirutla.movflex.core.data.source.local.dao.TvDao
import com.onirutla.movflex.core.data.source.local.entities.tv.TvEntity
import com.onirutla.movflex.core.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvLocalDataSource @Inject constructor(
    private val dao: TvDao,
) {
    fun observeFavoriteState(tvId: Int): Flow<Boolean> = dao.observeFavoriteState(tvId)
        .map { it != null }

    suspend fun isInDb(tvId: Int) = dao.isInDb(tvId)

    suspend fun addToFavorite(vararg tv: TvEntity) {
        dao.addToFavorite(*tv)
    }

    suspend fun deleteFavorite(vararg tv: TvEntity) = dao.deleteFavorite(*tv)

    fun getFavorite(): Flow<PagingData<TvEntity>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            dao.getFavorite()
        }
    ).flow
        .filterNotNull()
}
