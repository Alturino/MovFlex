package com.onirutla.movflex.core.data.source.remote.response.cast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditResponse(
    @Json(name = "cast")
    val cast: List<CastResponse>,
    @Json(name = "id")
    val id: Int,
)
