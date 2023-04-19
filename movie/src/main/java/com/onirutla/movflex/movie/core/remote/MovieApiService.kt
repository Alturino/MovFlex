package com.onirutla.movflex.movie.core.remote

import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.PageResponse
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(value = "movie/popular")
    suspend fun getMoviePopular(
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/top_rated")
    suspend fun getMovieTopRated(
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/{movieId}")
    suspend fun getMovieDetail(
        @Path(value = "movieId") movieId: Int,
    ): Response<MovieDetailResponse>

    @GET(value = "movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/{movieId}/similar")
    suspend fun getMovieSimilar(
        @Path(value = "movieId") movieId: Int,
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/{movieId}/recommendations")
    suspend fun getMovieRecommendations(
        @Path(value = "movieId") movieId: Int,
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<MovieResponse>>

    @GET(value = "movie/{movieId}/credits")
    suspend fun getMovieCasts(
        @Path(value = "movieId") movieId: Int,
    ): Response<CastResponse>

}
