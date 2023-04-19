package com.onirutla.movflex.tv.core.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.tv.domain.usecase.TvMoreUseCase
import com.onirutla.movflex.tv.domain.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.TvType
import com.onirutla.movflex.tv.domain.model.TvType.TV_AIRING_TODAY
import com.onirutla.movflex.tv.domain.model.TvType.TV_ON_THE_AIR
import com.onirutla.movflex.tv.domain.model.TvType.TV_POPULAR
import com.onirutla.movflex.tv.domain.model.TvType.TV_TOP_RATED
import javax.inject.Inject

class TvMoreUseCaseImpl @Inject constructor(
    private val tvRepository: TvRepository,
) : TvMoreUseCase {

    override fun invoke(tvType: TvType): LiveData<PagingData<Content>> =
        when (tvType) {
            TV_TOP_RATED -> tvRepository.getTvTopRatedPaging().asLiveData()
            TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging().asLiveData()
            TV_POPULAR -> tvRepository.getTvPopularPaging().asLiveData()
            TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging().asLiveData()
        }
}
