package com.onirutla.movflex.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.ui.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val popular = tvRepository.getTvPopularHome()
    private val airingToday = tvRepository.getTvAiringTodayHome()
    private val topRated = tvRepository.getTvTopRatedHome()
    private val onTheAir = tvRepository.getTvOnTheAirHome()

    val tvHome = combine(
        popular,
        airingToday,
        topRated,
        onTheAir
    ) { popular, airingToday, topRated, onTheAir ->

        val popularCategory = Category("Popular", popular)
        val airingTodayCategory = Category("Airing Today", airingToday)
        val topRatedCategory = Category("Top Rated", topRated)
        val onTheAirCategory = Category("On The Air", onTheAir)

        listOf(
            popularCategory,
            airingTodayCategory,
            topRatedCategory,
            onTheAirCategory
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}