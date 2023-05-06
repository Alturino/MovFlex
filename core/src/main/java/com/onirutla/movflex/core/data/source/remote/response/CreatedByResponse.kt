package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.CreatedBy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatedByResponse(
    @Json(name = "credit_id")
    val creditId: String? = "",
    @Json(name = "gender")
    val gender: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "profile_path")
    val profilePath: String? = "",
)

