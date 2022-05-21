package com.onirutla.movflex.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(vararg favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(vararg favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    fun getFavorite(): PagingSource<Int, FavoriteEntity>
}
