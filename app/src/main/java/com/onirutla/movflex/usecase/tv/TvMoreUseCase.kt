package com.onirutla.movflex.usecase.tv

import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.util.TvType
import javax.inject.Inject

class TvMoreUseCase @Inject constructor(
    private val tvRepository: TvRepository
) {

    operator fun invoke(tvType: TvType) = when (tvType) {
        TvType.TV_TOP_RATED -> tvRepository.getTvTopRatedPaging()
        TvType.TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging()
        TvType.TV_POPULAR -> tvRepository.getTvPopularPaging()
        TvType.TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging()
    }
}