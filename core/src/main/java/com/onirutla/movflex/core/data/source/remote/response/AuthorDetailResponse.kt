package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetailResponse(
    @Json(name = "avatar_path")
    val avatarPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "username")
    val username: String,
)

