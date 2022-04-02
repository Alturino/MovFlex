package com.onirutla.movflex.data.source.remote.response.tv


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvResponse(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "poster_path")
    val posterPath: String = "",
)

/*
fun TvResponse.toEntity() = TvEntity(
    backdropPath,
    firstAirDate,
    genreIds,
    id,
    name,
    originCountry,
    originalLanguage,
    originalName,
    overview,
    popularity,
    posterPath,
    voteAverage,
    voteCount
)*/
