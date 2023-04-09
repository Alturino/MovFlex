package com.onirutla.movflex.core.data.source.remote.response.shared

import com.onirutla.movflex.core.domain.model.shared.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
)

fun GenreResponse.toGenre(): Genre = Genre(
    id = id,
    name = name
)

fun List<GenreResponse>.toGenre(): List<Genre> = map { it.toGenre() }
