package com.onirutla.movflex.core.data.source.remote.response.shared

import com.onirutla.movflex.core.domain.model.shared.ProductionCountry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountryResponse(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "name")
    val name: String,
)

fun ProductionCountryResponse.toProductionCountry(): ProductionCountry = ProductionCountry(
    iso31661 = iso31661,
    name = name
)

fun List<ProductionCountryResponse>.toProductionCountry(): List<ProductionCountry> = this.map {
    it.toProductionCountry()
}
