package com.onirutla.movflex.domain.usecase.tv

import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface TvUseCase {

    operator fun invoke(): Flow<List<SeeMore<List<Content>>>>

}