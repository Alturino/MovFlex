package com.onirutla.movflex.core.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Upsert
    suspend fun addToFavorite(vararg favorites: MovieEntity)

    @Delete
    suspend fun deleteFavorite(vararg favorites: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :id")
    fun observeFavoriteState(id: Int): Flow<MovieEntity?>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun isInDb(id: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    fun getFavorite(): PagingSource<Int, MovieEntity>
}

