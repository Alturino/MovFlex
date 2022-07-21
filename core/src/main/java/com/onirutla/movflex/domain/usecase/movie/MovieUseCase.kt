package com.onirutla.movflex.domain.usecase.movie

import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    val movies: Flow<List<SeeMore<List<Content>>>>

}