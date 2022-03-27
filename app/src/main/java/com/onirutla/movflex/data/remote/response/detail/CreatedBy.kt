package com.onirutla.movflex.data.remote.response.detail

import com.squareup.moshi.Json

data class CreatedBy(
    @Json(name = "credit_id")
    val creditId: String = "",
    @Json(name = "gender")
    val gender: Int = 0,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "profile_path")
    val profilePath: String = ""
)