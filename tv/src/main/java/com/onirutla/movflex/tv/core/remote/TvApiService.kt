package com.onirutla.movflex.tv.core.remote

import com.onirutla.movflex.core.data.source.remote.response.cast.CreditResponse
import com.onirutla.movflex.core.data.source.remote.response.review.ReviewResponses
import com.onirutla.movflex.core.data.source.remote.response.shared.PageResponse
import com.onirutla.movflex.core.domain.model.shared.Season
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponseDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET(value = "tv/popular")
    suspend fun getTvPopular(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/on_the_air")
    suspend fun getTvOnTheAir(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/top_rated")
    suspend fun getTvTopRated(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/{tvId}")
    suspend fun getTvDetail(@Path(value = "tvId") tvId: Int): Response<TvResponseDetail>

    @GET(value = "tv/airing_today")
    suspend fun getTvAiringToday(@Query(value = "page") page: Int = 1): Response<PageResponse<TvResponse>>

    @GET(value = "tv/{tvId}/aggregate_credits")
    suspend fun getTvCredits(@Path(value = "tvId") tvId: Int): Response<CreditResponse>

    @GET(value = "tv/{tvId}/reviews")
    suspend fun getTvReviews(
        @Path(value = "tvId") tvId: Int,
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<ReviewResponses>>

    @GET(value = "tv/{tvId}/similar")
    suspend fun getTvSimilar(
        @Path(value = "tvId") tvId: Int,
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<TvResponse>>

    @GET(value = "tv/{tvId}/recommendations")
    suspend fun getTvRecommendations(
        @Path(value = "tvId") tvId: Int,
        @Query(value = "page") page: Int = 1,
    ): Response<PageResponse<TvResponse>>

    @GET(value = "tv/{tvId}/season/{seasonNumber}")
    suspend fun getTvSeason(
        @Path(value = "tvId") tvId: Int,
        @Path(value = "seasonNumber") seasonNumber: Int,
    ): Response<Season>
}
