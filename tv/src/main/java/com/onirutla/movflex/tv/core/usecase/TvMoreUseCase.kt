package com.onirutla.movflex.tv.core.usecase

import androidx.paging.PagingData
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType
import com.onirutla.movflex.tv.domain.model.TvType.TV_AIRING_TODAY
import com.onirutla.movflex.tv.domain.model.TvType.TV_ON_THE_AIR
import com.onirutla.movflex.tv.domain.model.TvType.TV_POPULAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_RECOMMENDATIONS
import com.onirutla.movflex.tv.domain.model.TvType.TV_SIMILAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_TOP_RATED
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvMoreUseCase @Inject constructor(
    private val tvRepository: TvRepository,
) {

    operator fun invoke(tvType: TvType, tvId: Int): Flow<PagingData<Tv>> = when (tvType) {
        TV_TOP_RATED -> tvRepository.getTvTopRatedPaging()
        TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging()
        TV_POPULAR -> tvRepository.getTvPopularPaging()
        TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging()
        TV_SIMILAR -> tvRepository.getTvSimilarPaging(tvId)
        TV_RECOMMENDATIONS -> tvRepository.getTvRecommendationsPaging(tvId)
    }
}
