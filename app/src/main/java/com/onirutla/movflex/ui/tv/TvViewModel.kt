package com.onirutla.movflex.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.ui.SeeMore
import com.onirutla.movflex.usecase.tv.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    useCase: TvUseCase
) : ViewModel() {

    val tvHome: LiveData<List<SeeMore<List<ItemDto>>>> = useCase.invoke()
        .asLiveData(viewModelScope.coroutineContext)

}