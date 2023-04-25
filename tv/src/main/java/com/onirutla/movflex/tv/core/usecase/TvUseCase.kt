package com.onirutla.movflex.tv.core.usecase

import android.util.Log
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
            val popular = async { repository.getTvPopularHome() }
            val airingToday = async { repository.getTvAiringTodayHome() }
            val onTheAir = async { repository.getTvOnTheAirHome() }
            val topRated = async { repository.getTvTopRatedHome() }

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
