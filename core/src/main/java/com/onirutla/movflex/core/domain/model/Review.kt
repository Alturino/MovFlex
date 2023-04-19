package com.onirutla.movflex.core.domain.model

data class Review(
    val author: String,
    val authorDetail: AuthorDetail,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)
