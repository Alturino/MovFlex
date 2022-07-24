package com.onirutla.movflex.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.type.TvType

interface TvMoreUseCase  {

    operator fun invoke(tvType: TvType): LiveData<PagingData<Content>>
}