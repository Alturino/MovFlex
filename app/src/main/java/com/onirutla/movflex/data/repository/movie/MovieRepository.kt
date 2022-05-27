package com.onirutla.movflex.data.repository.movie

import androidx.paging.PagingData
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopularPaging(): Flow<PagingData<ItemResponse>>
    fun getMoviePopularHome(): Flow<List<ItemResponse>>

    fun getMovieNowPlayingPaging(): Flow<PagingData<ItemResponse>>
    fun getMovieNowPlayingHome(): Flow<List<ItemResponse>>

    fun getMovieTopRatedPaging(): Flow<PagingData<ItemResponse>>
    fun getMovieTopRatedHome(): Flow<List<ItemResponse>>

    fun getMovieUpcomingPaging(): Flow<PagingData<ItemResponse>>
    fun getMovieUpcomingHome(): Flow<List<ItemResponse>>

    fun getMovieDetail(id: Int): Flow<FavoriteEntity>

    suspend fun setFavorite(movie: FavoriteEntity)
}