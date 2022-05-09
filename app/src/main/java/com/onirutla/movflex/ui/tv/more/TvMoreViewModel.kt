package com.onirutla.movflex.ui.tv.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.util.Constants.TITLE_POPULAR
import com.onirutla.movflex.util.Constants.TITLE_TV_AIRING_TODAY
import com.onirutla.movflex.util.Constants.TITLE_TV_ON_THE_AIR
import com.onirutla.movflex.util.Constants.TITLE_TV_TOP_RATED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvMoreViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _tv = MutableStateFlow<PagingData<ItemResponse>>(PagingData.empty())
    val tv get() = _tv.asStateFlow()

    fun getTvByCategory(category: String) {
        viewModelScope.launch {
            when (category) {
                TITLE_TV_TOP_RATED -> {
                    tvRepository.getTvTopRatedPaging().cachedIn(viewModelScope).collect {
                        _tv.value = it
                    }
                }
                TITLE_TV_ON_THE_AIR -> {
                    tvRepository.getTvOnTheAirPaging().cachedIn(viewModelScope).collect {
                        _tv.value = it
                    }
                }
                TITLE_POPULAR -> {
                    tvRepository.getTvPopularPaging().cachedIn(viewModelScope).collect {
                        _tv.value = it
                    }
                }
                TITLE_TV_AIRING_TODAY -> {
                    tvRepository.getTvAiringTodayPaging().cachedIn(viewModelScope).collect {
                        _tv.value = it
                    }
                }
            }
        }
    }


}