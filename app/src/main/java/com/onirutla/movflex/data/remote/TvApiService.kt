package com.onirutla.movflex.data.remote

import com.onirutla.movflex.data.PageResponse
import com.onirutla.movflex.data.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET(value = "tv/popular")
    suspend fun getTvPopular(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/now_playing")
    suspend fun getTvNowPlaying(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/top_rated")
    suspend fun getTvTopRated(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/{tv_id}")
    suspend fun getTvDetail(@Path(value = "tv_id") tvId: Int): Response<PageResponse<TvResponse>>

    @GET(value = "tv/upcoming")
    suspend fun getTvUpcoming(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

}