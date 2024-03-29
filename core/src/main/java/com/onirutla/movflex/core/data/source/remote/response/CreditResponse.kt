package com.onirutla.movflex.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditResponse(
    @Json(name = "cast")
    val cast: List<CastResponse>? = emptyList(),
    @Json(name = "id")
    val id: Int? = 0,
)
