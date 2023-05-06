package com.onirutla.movflex.tv.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType
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
            val popular = async { repository.getTvPopular() }
            val airingToday = async { repository.getTvAiringToday() }
            val onTheAir = async { repository.getTvOnTheAir() }
            val topRated = async { repository.getTvTopRated() }

            val popularSeeMore = SeeMore(TvType.TV_POPULAR.value, popular.await())
            val airingTodaySeeMore = SeeMore(TvType.TV_AIRING_TODAY.value, airingToday.await())
            val onTheAirSeeMore = SeeMore(TvType.TV_ON_THE_AIR.value, onTheAir.await())
            val topRatedSeeMore = SeeMore(TvType.TV_TOP_RATED.value, topRated.await())


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
