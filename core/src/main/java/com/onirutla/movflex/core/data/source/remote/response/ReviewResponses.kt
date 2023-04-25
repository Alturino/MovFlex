package com.onirutla.movflex.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponses(
    @Json(name = "id")
    val id: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val reviews: List<ReviewResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int,
)
