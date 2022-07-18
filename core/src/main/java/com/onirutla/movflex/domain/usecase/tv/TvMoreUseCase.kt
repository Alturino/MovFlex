package com.onirutla.movflex.domain.usecase.tv

import androidx.lifecycle.asLiveData
import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.domain.model.type.TvType
import javax.inject.Inject

class TvMoreUseCase @Inject constructor(
    private val tvRepository: TvRepository
) {

    operator fun invoke(tvType: TvType) = when (tvType) {
        TvType.TV_TOP_RATED -> tvRepository.getTvTopRatedPaging().asLiveData()
        TvType.TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging().asLiveData()
        TvType.TV_POPULAR -> tvRepository.getTvPopularPaging().asLiveData()
        TvType.TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging().asLiveData()
    }
}