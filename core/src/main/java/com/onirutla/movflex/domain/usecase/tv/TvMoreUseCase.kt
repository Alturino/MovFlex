package com.onirutla.movflex.domain.usecase.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.onirutla.movflex.data.repository.tv.TvRepository
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.domain.model.type.TvType
import javax.inject.Inject

interface TvMoreUseCase  {

    operator fun invoke(tvType: TvType): LiveData<PagingData<Content>>
}