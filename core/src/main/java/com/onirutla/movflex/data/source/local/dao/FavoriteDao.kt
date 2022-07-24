package com.onirutla.movflex.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorite(vararg favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(vararg favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite WHERE is_favorite = 1")
    fun getFavorite(): PagingSource<Int, FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE id = :id")
    suspend fun isFavorite(id: Int): FavoriteEntity?
}
