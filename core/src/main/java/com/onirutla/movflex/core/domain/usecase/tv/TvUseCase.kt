package com.onirutla.movflex.core.domain.usecase.tv

import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface TvUseCase {

    operator fun invoke(): Flow<List<SeeMore<List<Content>>>>

}
