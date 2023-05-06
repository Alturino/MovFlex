package com.onirutla.movflex.tv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.tv.core.repository.TvRepository
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val repository: TvRepository,
) : ViewModel() {

    private val _tvId = MutableLiveData<Int>()

    val tvDetail: LiveData<TvDetail> = _tvId.switchMap {
        repository.getTvDetail(it).asLiveData(viewModelScope.coroutineContext)
    }

    val tvRecommendations: LiveData<List<Tv>> = _tvId.switchMap {
        liveData { emit(repository.getTvRecommendations(it)) }
    }

    val tvReviews: LiveData<List<Review>> = _tvId.switchMap {
        liveData { emit(repository.getTvReview(it)) }
    }

    val tvCasts: LiveData<List<Cast>> = _tvId.switchMap {
        liveData { emit(repository.getTvCast(it)) }
    }

    val tvSimilar: LiveData<List<Tv>> = _tvId.switchMap {
        liveData { emit(repository.getTvSimilar(it)) }
    }

    val tvSeason: LiveData<List<Season>> = _tvId.switchMap {
        liveData { emit(repository.getTvSeason(it)) }
    }

    val isFavorite = _tvId.switchMap {
        repository.observeFavoriteState(it).asLiveData(viewModelScope.coroutineContext)
    }

    fun setFavorite(tv: TvDetail) {
        viewModelScope.launch {
            repository.setFavorite(tv)
        }
    }

    fun getTvDetail(id: Int) {
        viewModelScope.launch {
            _tvId.value = id
        }
    }

}
