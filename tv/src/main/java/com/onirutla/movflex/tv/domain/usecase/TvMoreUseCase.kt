package com.onirutla.movflex.tv.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.tv.domain.model.TvType

interface TvMoreUseCase {

    operator fun invoke(tvType: TvType): LiveData<PagingData<Content>>
}
