package com.onirutla.movflex.core.domain.usecase.movie

import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    val movies: Flow<List<SeeMore<List<Content>>>>

}