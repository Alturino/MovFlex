package com.onirutla.movflex.tv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.core.usecase.TvUseCase
import com.onirutla.movflex.tv.domain.model.Tv
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    useCase: TvUseCase,
) : ViewModel() {

    val tvHome: LiveData<List<SeeMore<List<Tv>>>> = useCase.tv
        .asLiveData(viewModelScope.coroutineContext)

}
