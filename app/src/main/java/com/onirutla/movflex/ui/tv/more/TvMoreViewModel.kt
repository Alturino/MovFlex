package com.onirutla.movflex.ui.tv.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.type.TvType
import com.onirutla.movflex.core.domain.usecase.tv.TvMoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvMoreViewModel @Inject constructor(
    private val tvMoreUseCase: TvMoreUseCase
) : ViewModel() {

    private val _tvType: MutableLiveData<TvType> = MutableLiveData<TvType>()

    val tvMore: LiveData<PagingData<Content>> = _tvType.switchMap {
        tvMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getTvByCategory(tvType: TvType) {
        _tvType.value = tvType
    }

}