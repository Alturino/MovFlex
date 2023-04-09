package com.onirutla.movflex.core.domain.model.shared

data class GuestStar(
    val adult: Boolean,
    val character: String,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String,
)
