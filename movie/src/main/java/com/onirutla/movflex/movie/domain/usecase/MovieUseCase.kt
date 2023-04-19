package com.onirutla.movflex.movie.domain.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    val movies: Flow<List<SeeMore<List<Content>>>>
}
