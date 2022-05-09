package com.onirutla.movflex.ui.movie.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onirutla.movflex.data.repository.movie.MovieRepository
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.util.Constants.TITLE_NOW_PLAYING
import com.onirutla.movflex.util.Constants.TITLE_POPULAR
import com.onirutla.movflex.util.Constants.TITLE_TOP_RATED
import com.onirutla.movflex.util.Constants.TITLE_UPCOMING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MovieMoreViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow<PagingData<ItemResponse>>(PagingData.empty())
    val movie get() = _movie.asStateFlow()

    fun getMovieByCategory(category: String) {
        viewModelScope.launch {
            when (category) {
                TITLE_TOP_RATED -> {
                    movieRepository.getMovieTopRatedPaging().cachedIn(viewModelScope).collect {
                        _movie.value = it
                    }
                }
                TITLE_NOW_PLAYING -> {
                    movieRepository.getMovieNowPlayingPaging().cachedIn(viewModelScope).collect {
                        _movie.value = it
                    }
                }
                TITLE_POPULAR -> {
                    movieRepository.getMoviePopularPaging().cachedIn(viewModelScope).collect {
                        _movie.value = it
                    }
                }
                TITLE_UPCOMING -> {
                    movieRepository.getMovieUpcomingPaging().cachedIn(viewModelScope).collect {
                        _movie.value = it
                    }
                }
            }
        }
    }


}