package com.onirutla.movflex.tv.core.usecase

import android.content.Context
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class TvUseCase @Inject constructor(
    repository: TvRepository,
    @ApplicationContext context: Context,
) {

    val tv: Flow<List<SeeMore<List<Tv>>>> = flow {
        val result = supervisorScope {
            val popular = async { repository.getTvPopular() }
            val airingToday = async { repository.getTvAiringToday() }
            val onTheAir = async { repository.getTvOnTheAir() }
            val topRated = async { repository.getTvTopRated() }

            val popularSeeMore = SeeMore(
                title = context.getString(TvType.TV_POPULAR.value),
                items = popular.await()
            )

            val airingTodaySeeMore = SeeMore(
                title = context.getString(TvType.TV_AIRING_TODAY.value),
                items = airingToday.await()
            )

            val onTheAirSeeMore = SeeMore(
                title = context.getString(TvType.TV_ON_THE_AIR.value),
                items = onTheAir.await()
            )

            val topRatedSeeMore = SeeMore(
                title = context.getString(TvType.TV_TOP_RATED.value),
                items = topRated.await()
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
