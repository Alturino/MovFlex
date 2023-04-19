package com.onirutla.movflex.core.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun addToFavorite(vararg favorites: MovieEntity)

    @Delete
    suspend fun deleteFavorite(vararg favorites: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun isFavorite(id: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    fun getFavorite(): PagingSource<Int, MovieEntity>
}

