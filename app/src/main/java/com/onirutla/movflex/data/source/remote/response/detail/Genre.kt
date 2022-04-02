package com.onirutla.movflex.data.source.remote.response.detail

import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = ""
)