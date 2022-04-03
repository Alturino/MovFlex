package com.onirutla.movflex.data.source.remote.service

import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.data.source.remote.response.detail.PageResponse
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponseDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(value = "movie/popular")
    suspend fun getMoviePopular(@Query(value = "page") page: Int = 1): Response<PageResponse<ItemResponse>>

    @GET(value = "movie/now_playing")
    suspend fun getMovieNowPlaying(@Query(value = "page") page: Int = 1): Response<PageResponse<ItemResponse>>

    @GET(value = "movie/top_rated")
    suspend fun getMovieTopRated(@Query(value = "page") page: Int = 1): Response<PageResponse<ItemResponse>>

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetail(@Path(value = "movie_id") movieId: Int): Response<MovieResponseDetail>

    @GET(value = "movie/upcoming")
    suspend fun getMovieUpcoming(@Query(value = "page") page: Int = 1): Response<PageResponse<ItemResponse>>

}