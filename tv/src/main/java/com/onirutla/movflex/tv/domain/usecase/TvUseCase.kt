package com.onirutla.movflex.tv.domain.usecase

import com.onirutla.movflex.core.domain.model.SeeMore
import kotlinx.coroutines.flow.Flow

interface TvUseCase {

    operator fun invoke(): Flow<List<SeeMore<List<Content>>>>

}
