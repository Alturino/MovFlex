package com.onirutla.movflex.core.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.type.MovieType

interface MovieMoreUseCase {

    operator fun invoke(movieType: MovieType): LiveData<PagingData<Content>>

}