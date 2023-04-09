package com.onirutla.movflex.core.data.source.remote.response.shared

import com.onirutla.movflex.core.domain.model.shared.CreatedBy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatedByResponse(
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String,
)

fun CreatedByResponse.toCreatedBy(): CreatedBy = CreatedBy(
    creditId = creditId,
    gender = gender,
    id = id,
    name = name,
    profilePath = profilePath
)

fun List<CreatedByResponse>.toCreatedBy(): List<CreatedBy> = this.map { it.toCreatedBy() }
