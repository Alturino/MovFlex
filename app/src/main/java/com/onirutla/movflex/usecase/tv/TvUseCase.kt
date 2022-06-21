package com.onirutla.movflex.usecase.tv

import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.ui.SeeMore
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

    operator fun invoke(): Flow<List<SeeMore<List<ItemResponse>>>> = combine(
        popular,
        airingToday,
        onTheAir,
        topRated
    ) { popular, airingToday, onTheAir, topRated ->

        val popularSeeMore = SeeMore("Popular", popular)
        val upcomingSeeMore = SeeMore("Airing Today", airingToday)
        val topRatedSeeMore = SeeMore("On The Air", onTheAir)
        val nowPlayingSeeMore = SeeMore("Top Rated", topRated)

        listOf(
            popularSeeMore,
            upcomingSeeMore,
            topRatedSeeMore,
            nowPlayingSeeMore
        )

    }
}