package com.onirutla.movflex.core.util

import com.onirutla.movflex.core.data.source.remote.response.tv.TvResponse
import com.onirutla.movflex.core.data.source.remote.response.tv.TvResponseDetail
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.TvContent

fun TvResponse.toContent(): Content =
    TvContent(
        id = id,
        backdropPath = posterPath.orEmpty(),
        title = name.orEmpty(),
        description = "",
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        isFavorite = false,
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
    )

