package com.onirutla.movflex.data.remote.service

import com.onirutla.movflex.data.remote.response.detail.PageResponse
import com.onirutla.movflex.data.remote.response.tv.TvResponse
import com.onirutla.movflex.data.remote.response.tv.TvResponseDetail
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
    suspend fun getTvDetail(@Path(value = "tv_id") tvId: Int): Response<TvResponseDetail>

    @GET(value = "tv/upcoming")
    suspend fun getTvUpcoming(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

}