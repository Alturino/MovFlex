package com.onirutla.movflex.core.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.type.TvType

interface TvMoreUseCase {

    operator fun invoke(tvType: TvType): LiveData<PagingData<Content>>
}
