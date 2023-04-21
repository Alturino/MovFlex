package com.onirutla.movflex.tv.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class TvUseCase @Inject constructor(
    repository: TvRepository,
) {

    val tv: Flow<List<SeeMore<List<Tv>>>> = flow {
        val result = supervisorScope {
            val popular = async { repository.getTvPopularHome() }
            val airingToday = async { repository.getTvAiringTodayHome() }
            val onTheAir = async { repository.getTvOnTheAirHome() }
            val topRated = async { repository.getTvTopRatedHome() }

            val popularSeeMore = SeeMore("Popular", popular.await())
            val airingTodaySeeMore = SeeMore("Airing Today", airingToday.await())
            val onTheAirSeeMore = SeeMore("On The Air", onTheAir.await())
            val topRatedSeeMore = SeeMore("Top Rated", topRated.await())

            listOf(
                topRatedSeeMore,
                popularSeeMore,
                airingTodaySeeMore,
                onTheAirSeeMore,
            )
        }
        emit(result)
    }

}
