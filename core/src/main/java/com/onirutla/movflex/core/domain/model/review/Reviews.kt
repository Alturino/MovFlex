package com.onirutla.movflex.core.domain.model.review

data class Reviews(
    val id: Int,
    val page: Int,
    val reviews: List<Review>,
    val totalPages: Int,
    val totalResults: Int,
)
