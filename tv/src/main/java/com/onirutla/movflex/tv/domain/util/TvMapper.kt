package com.onirutla.movflex.tv.domain.util

import com.onirutla.movflex.core.data.ItemType
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponseDetail
import com.onirutla.movflex.tv.domain.model.TvContent

fun TvResponse.toContent(): Content =
    TvContent(
        id = id,
        backdropPath = posterPath.orEmpty(),
        title = name.orEmpty(),
        description = "",
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        isFavorite = false,
        type = ItemType.Tv
    )

fun TvResponseDetail.toContent(): Content =
    TvContent(
        id = id ?: 0,
        backdropPath = posterPath.orEmpty(),
        title = name.orEmpty(),
        description = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        isFavorite = false,
        type = ItemType.Tv
    )
