package com.onirutla.movflex.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.SeeMore
import com.onirutla.movflex.domain.usecase.tv.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    useCase: TvUseCase
) : ViewModel() {

    val tvHome: LiveData<List<SeeMore<List<Content>>>> =
        useCase.invoke()
            .asLiveData(viewModelScope.coroutineContext)

}