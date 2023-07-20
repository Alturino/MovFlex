package com.onirutla.movflex.tv.core.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvUseCase @Inject constructor(
    repository: TvRepository,
) {

    val tv: Flow<List<SeeMore<List<Tv>>>> = flow {
        val result = supervisorScope {
            val popular = withContext(coroutineContext) { repository.getTvPopular() }
            val airingToday = withContext(coroutineContext) { repository.getTvAiringToday() }
            val onTheAir = withContext(coroutineContext) { repository.getTvOnTheAir() }
            val topRated = withContext(coroutineContext) { repository.getTvTopRated() }

            val popularSeeMore = SeeMore(
                title = TvType.TV_POPULAR.value,
                items = popular
            )

            val airingTodaySeeMore = SeeMore(
                title = TvType.TV_AIRING_TODAY.value,
                items = airingToday
            )

            val onTheAirSeeMore = SeeMore(
                title = TvType.TV_ON_THE_AIR.value,
                items = onTheAir
            )

            val topRatedSeeMore = SeeMore(
                title = TvType.TV_TOP_RATED.value,
                items = topRated
            )

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
