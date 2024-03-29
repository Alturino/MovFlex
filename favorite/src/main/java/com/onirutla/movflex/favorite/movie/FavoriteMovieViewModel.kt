package com.onirutla.movflex.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.movie.core.repository.MovieRepository
import com.onirutla.movflex.movie.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movieFavorite: Flow<PagingData<Movie>> = repository.getMovieFavorite()
        .cachedIn(viewModelScope)

}
