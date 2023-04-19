package com.onirutla.movflex.movie.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.movie.domain.model.MovieType

interface MovieMoreUseCase {
    operator fun invoke(movieType: MovieType): LiveData<PagingData<Content>>
}
