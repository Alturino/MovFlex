package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrewResponse(
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "credit_id")
    val creditId: String? = "",
    @Json(name = "department")
    val department: String? = "",
    @Json(name = "gender")
    val gender: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "job")
    val job: String? = "",
    @Json(name = "known_for_department")
    val knownForDepartment: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "original_name")
    val originalName: String? = "",
    @Json(name = "popularity")
    val popularity: Double? = 0.0,
    @Json(name = "profile_path")
    val profilePath: String? = "",
)

