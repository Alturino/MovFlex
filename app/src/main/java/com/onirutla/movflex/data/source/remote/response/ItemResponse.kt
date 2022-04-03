package com.onirutla.movflex.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemResponse(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
)