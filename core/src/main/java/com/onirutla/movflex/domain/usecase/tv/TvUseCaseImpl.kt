package com.onirutla.movflex.domain.usecase.tv

import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class TvUseCaseImpl @Inject constructor(
    tvRepository: TvRepository
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