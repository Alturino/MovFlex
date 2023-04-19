package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "author")
    val author: String,
    @Json(name = "author_details")
    val authorDetailsResponse: AuthorDetail,
    @Json(name = "content")
    val content: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String,
)
