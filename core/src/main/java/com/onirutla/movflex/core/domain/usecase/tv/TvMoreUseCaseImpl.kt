package com.onirutla.movflex.core.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.type.TvType
import com.onirutla.movflex.core.domain.repository.TvRepository
import javax.inject.Inject

class TvMoreUseCaseImpl @Inject constructor(
    private val tvRepository: TvRepository
) : TvMoreUseCase {

    override fun invoke(tvType: TvType): LiveData<PagingData<Content>> = when (tvType) {
        TvType.TV_TOP_RATED -> tvRepository.getTvTopRatedPaging().asLiveData()
        TvType.TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging().asLiveData()
        TvType.TV_POPULAR -> tvRepository.getTvPopularPaging().asLiveData()
        TvType.TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging().asLiveData()
    }
}