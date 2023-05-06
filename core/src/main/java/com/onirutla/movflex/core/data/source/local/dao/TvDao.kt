package com.onirutla.movflex.core.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.onirutla.movflex.core.data.source.local.entities.tv.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Upsert
    suspend fun addToFavorite(vararg favorites: TvEntity)

    @Delete
    suspend fun deleteFavorite(vararg favorites: TvEntity)

    @Query("SELECT * FROM tv WHERE id = :id")
    suspend fun isInDb(id: Int): TvEntity?

    @Query("SELECT * FROM tv WHERE id= :id")
    fun observeFavoriteState(id: Int): Flow<TvEntity?>

    @Query("SELECT * FROM tv")
    fun getFavorite(): PagingSource<Int, TvEntity>
}
