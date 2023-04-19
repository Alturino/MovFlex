package com.onirutla.movflex.tv.domain.remote

import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse

interface TvRemoteDataSource {

    suspend fun getTvPopular(page: Int = 1): List<TvResponse>

    suspend fun getTvOnTheAir(page: Int = 1): List<TvResponse>

    suspend fun getTvTopRated(page: Int = 1): List<TvResponse>

    suspend fun getTvDetail(tvId: Int): TvDetailResponse?

    suspend fun getTvAiringToday(page: Int = 1): List<TvResponse>

}
