package com.onirutla.movflex.ui.tv.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.usecase.tv.TvMoreUseCase
import com.onirutla.movflex.util.TvType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvMoreViewModel @Inject constructor(
    private val tvMoreUseCase: TvMoreUseCase
) : ViewModel() {

    private val _tvType: MutableLiveData<TvType> = MutableLiveData<TvType>()

    val tvMore: LiveData<PagingData<ItemResponse>> = _tvType.switchMap {
        tvMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getTvByCategory(category: String) {
        _category.value = category
    }

}