package com.onirutla.movflex.movie.core.remote

import com.onirutla.movflex.core.data.source.remote.response.detail.PageResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponseDetail
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

    @GET(value = "movie/{movieId}")
    suspend fun getMovieDetail(@Path(value = "movieId") movieId: Int): Response<MovieResponseDetail>

    @GET(value = "movie/upcoming")
    suspend fun getMovieUpcoming(@Query(value = "page") page: Int = 1): Response<PageResponse<MovieResponse>>

}
