package com.onirutla.movflex.core.util

import com.onirutla.movflex.core.data.source.remote.response.AuthorDetailResponse
import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.ReviewResponse
import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.Review

fun CastResponse.toCast() = Cast(
    adult = adult ?: false,
    castId = castId ?: 0,
    character = character.orEmpty(),
    creditId = creditId.orEmpty(),
    gender = gender ?: 0,
    id = id ?: 0,
    knownForDepartment = knownForDepartment.orEmpty(),
    name = name.orEmpty(),
    order = order ?: 0,
    originalName = originalName.orEmpty(),
    popularity = popularity ?: 0.0,
    profilePath = profilePath.orEmpty()
)

fun List<CastResponse>.toCasts() = this.map { it.toCast() }

fun ReviewResponse.toReview() = Review(
    author = author.orEmpty(),
    authorDetail = authorDetailsResponse.toAuthorDetail(),
    content = content.orEmpty(),
    createdAt = createdAt.orEmpty(),
    id = id.orEmpty(),
    updatedAt = updatedAt.orEmpty(),
    url = url.orEmpty(),
)

fun List<ReviewResponse>.toReviews() = this.map { it.toReview() }

fun AuthorDetailResponse.toAuthorDetail() = AuthorDetail(
    avatarPath = avatarPath.orEmpty(),
    name = name.orEmpty(),
    rating = rating ?: 0,
    username = username.orEmpty(),
)
