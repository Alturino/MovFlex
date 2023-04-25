package com.onirutla.movflex.movie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.core.usecase.MovieUseCase
import com.onirutla.movflex.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    useCase: MovieUseCase,
) : ViewModel() {

    val movie: LiveData<List<SeeMore<List<Movie>>>> = useCase.movies
        .asLiveData(viewModelScope.coroutineContext)

}
