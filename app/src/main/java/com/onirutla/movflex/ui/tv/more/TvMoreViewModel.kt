package com.onirutla.movflex.ui.tv.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.usecase.tv.TvMoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvMoreViewModel @Inject constructor(
    private val tvMoreUseCase: TvMoreUseCase
) : ViewModel() {

    private val _category = MutableStateFlow("")

    val tvMore = _category.flatMapLatest {
        tvMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getTvByCategory(category: String) {
        _category.value = category
    }

}