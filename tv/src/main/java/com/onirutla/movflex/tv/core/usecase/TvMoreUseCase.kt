package com.onirutla.movflex.tv.core.usecase

import androidx.paging.PagingData
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.TvType
import com.onirutla.movflex.tv.domain.model.TvType.TV_AIRING_TODAY
import com.onirutla.movflex.tv.domain.model.TvType.TV_CASTS
import com.onirutla.movflex.tv.domain.model.TvType.TV_ON_THE_AIR
import com.onirutla.movflex.tv.domain.model.TvType.TV_POPULAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_RECOMMENDATIONS
import com.onirutla.movflex.tv.domain.model.TvType.TV_REVIEWS
import com.onirutla.movflex.tv.domain.model.TvType.TV_SIMILAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_TOP_RATED
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvMoreUseCase @Inject constructor(
    private val tvRepository: TvRepository,
) {

    operator fun invoke(tvType: TvType, tvId: Int): Flow<PagingData<Any>> = when (tvType) {
        TV_TOP_RATED -> tvRepository.getTvTopRatedPaging() as Flow<PagingData<Any>>
        TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging() as Flow<PagingData<Any>>
        TV_POPULAR -> tvRepository.getTvPopularPaging() as Flow<PagingData<Any>>
        TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging() as Flow<PagingData<Any>>
        TV_SIMILAR -> tvRepository.getTvSimilarPaging(tvId) as Flow<PagingData<Any>>
        TV_RECOMMENDATIONS -> tvRepository.getTvRecommendationsPaging(tvId) as Flow<PagingData<Any>>
        TV_CASTS -> tvRepository.getTvCastPaging(tvId) as Flow<PagingData<Any>>
        TV_REVIEWS -> tvRepository.getTvReviewPaging(tvId) as Flow<PagingData<Any>>
    }
}
