package com.onirutla.movflex.core.util

import com.onirutla.movflex.core.data.source.remote.response.AuthorDetailResponse
import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.ReviewResponse
import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review

fun CastResponse.toCast() = Cast(
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath.orEmpty()
)

fun List<CastResponse>.toCasts() = this.map { it.toCast() }

fun ReviewResponse.toReview() = Review(
    author = author,
    authorDetail = authorDetailsResponse.toAuthorDetail(),
    content = content,
    createdAt = createdAt,
    id = id,
    updatedAt = updatedAt,
    url = url,
)

fun List<ReviewResponse>.toReviews() = this.map { it.toReview() }

fun AuthorDetailResponse.toAuthorDetail() = AuthorDetail(
    avatarPath = avatarPath.orEmpty(),
    name = name.orEmpty(),
    rating = rating ?: 0,
    username = username.orEmpty(),
)
