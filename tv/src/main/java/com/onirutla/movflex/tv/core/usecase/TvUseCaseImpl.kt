package com.onirutla.movflex.tv.core.usecase

import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.domain.repository.TvRepository
import com.onirutla.movflex.tv.domain.usecase.TvUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class TvUseCaseImpl @Inject constructor(
    tvRepository: TvRepository,
) : TvUseCase {

    private val popular = tvRepository.getTvPopularHome()
    private val airingToday = tvRepository.getTvAiringTodayHome()
    private val onTheAir = tvRepository.getTvOnTheAirHome()
    private val topRated = tvRepository.getTvTopRatedHome()

    override fun invoke(): Flow<List<SeeMore<List<Content>>>> = combine(
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
