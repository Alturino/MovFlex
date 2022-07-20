package com.onirutla.movflex.domain.usecase.movie

import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.SeeMore
import com.onirutla.movflex.data.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface MovieUseCase {

    val movies: Flow<List<SeeMore<List<Content>>>>

}