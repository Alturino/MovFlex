package com.onirutla.movflex.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastResponse(
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "cast_id")
    val castId: Int? = 0,
    @Json(name = "character")
    val character: String? = "",
    @Json(name = "credit_id")
    val creditId: String? = "",
    @Json(name = "gender")
    val gender: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "known_for_department")
    val knownForDepartment: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "order")
    val order: Int? = 0,
    @Json(name = "original_name")
    val originalName: String? = "",
    @Json(name = "popularity")
    val popularity: Double? = 0.0,
    @Json(name = "profile_path")
    val profilePath: String? = "",
)
