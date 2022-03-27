package com.onirutla.movflex.data.remote

import com.onirutla.movflex.data.MovieResponse
import com.onirutla.movflex.data.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(value = "movie/popular")
    suspend fun getMoviePopular(@Query(value = "page") page: Int = 1): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/now_playing")
    suspend fun getMovieNowPlaying(@Query(value = "page") page: Int = 1): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/top_rated")
    suspend fun getMovieTopRated(@Query(value = "page") page: Int = 1): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetail(@Path(value = "movie_id") movieId: Int): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/upcoming")
    suspend fun getMovieUpcoming(@Query(value = "page") page: Int = 1): Response<PageResponse<MovieResponse>>

}