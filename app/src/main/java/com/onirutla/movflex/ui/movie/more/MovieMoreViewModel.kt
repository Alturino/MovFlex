package com.onirutla.movflex.ui.movie.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onirutla.movflex.usecase.movie.MovieMoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MovieMoreViewModel @Inject constructor(
    private val movieMoreUseCase: MovieMoreUseCase
) : ViewModel() {

    private val _category = MutableStateFlow("")

    val movieMore = _category.flatMapLatest {
        movieMoreUseCase.invoke(it).cachedIn(viewModelScope)
    }

    fun getMovieByCategory(category: String) {
        _category.value = category
    }


}