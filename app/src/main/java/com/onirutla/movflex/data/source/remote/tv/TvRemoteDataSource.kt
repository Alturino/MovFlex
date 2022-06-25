package com.onirutla.movflex.data.source.remote.tv

import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.data.source.remote.response.tv.TvResponseDetail

interface TvRemoteDataSource {

    suspend fun getTvPopular(page: Int = 1): List<ItemDto>

    suspend fun getTvOnTheAir(page: Int = 1): List<ItemDto>

    suspend fun getTvTopRated(page: Int = 1): List<ItemDto>

    suspend fun getTvDetail(tvId: Int): TvResponseDetail

    suspend fun getTvAiringToday(page: Int = 1): List<ItemDto>

}