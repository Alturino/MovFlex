package com.onirutla.movflex.usecase.tv

import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.ui.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class TvUseCase @Inject constructor(
    tvRepository: TvRepository
) {
    private val popular = tvRepository.getTvPopularHome()
    private val airingToday = tvRepository.getTvAiringTodayHome()
    private val onTheAir = tvRepository.getTvOnTheAirHome()
    private val topRated = tvRepository.getTvTopRatedHome()

    operator fun invoke(): Flow<List<Category<List<ItemResponse>>>> = combine(
        popular,
        airingToday,
        onTheAir,
        topRated
    ) { popular, airingToday, onTheAir, topRated ->

        val popularCategory = Category("Popular", popular)
        val upcomingCategory = Category("Airing Today", airingToday)
        val topRatedCategory = Category("On The Air", onTheAir)
        val nowPlayingCategory = Category("Top Rated", topRated)

        listOf(
            popularCategory,
            upcomingCategory,
            topRatedCategory,
            nowPlayingCategory
        )

    }
}