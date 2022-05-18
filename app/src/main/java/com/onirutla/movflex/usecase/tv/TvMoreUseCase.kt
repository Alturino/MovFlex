package com.onirutla.movflex.usecase.tv

import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.util.Constants.TITLE_POPULAR
import com.onirutla.movflex.util.Constants.TITLE_TV_AIRING_TODAY
import com.onirutla.movflex.util.Constants.TITLE_TV_ON_THE_AIR
import com.onirutla.movflex.util.Constants.TITLE_TV_TOP_RATED
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvMoreUseCase @Inject constructor(
    private val tvRepository: TvRepository
) {

    operator fun invoke(category: String) = when (category) {
        TITLE_TV_TOP_RATED -> tvRepository.getTvTopRatedPaging()
        TITLE_TV_ON_THE_AIR -> tvRepository.getTvOnTheAirPaging()
        TITLE_POPULAR -> tvRepository.getTvPopularPaging()
        TITLE_TV_AIRING_TODAY -> tvRepository.getTvAiringTodayPaging()
        else -> flow { }
    }
}