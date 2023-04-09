package com.onirutla.movflex.core.data.source.remote.response.shared

import com.onirutla.movflex.core.domain.model.shared.Network
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String,
)

fun NetworkResponse.toNetwork(): Network = Network(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)

fun List<NetworkResponse>.toNetwork(): List<Network> = this.map { it.toNetwork() }
