package com.onirutla.movflex.tv.ui.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.tv.core.usecase.TvMoreUseCase
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TvMoreViewModel @Inject constructor(
    private val tvMoreUseCase: TvMoreUseCase,
) : ViewModel() {

    private val _tvType: MutableStateFlow<TvType?> = MutableStateFlow(null)

    var tvId = 0

    val tvMore: Flow<PagingData<Tv>> = _tvType.flatMapLatest { tvType ->
        tvMoreUseCase(tvType ?: TvType.TV_ON_THE_AIR, tvId).cachedIn(viewModelScope)
    }

    fun getTvByCategory(tvType: TvType) {
        _tvType.value = tvType
    }

    fun getTvByCategory(tvType: String) {
        val enum = TvType.values().first { it.value == tvType }
        _tvType.value = enum
    }
}
