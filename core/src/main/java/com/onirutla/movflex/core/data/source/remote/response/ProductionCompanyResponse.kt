package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyResponse(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "logo_path")
    val logoPath: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "origin_country")
    val originCountry: String? = "",
)

fun ProductionCompanyResponse.toProductionCompany(): ProductionCompany = ProductionCompany(
    id = id ?: 0,
    logoPath = logoPath.orEmpty(),
    name = name.orEmpty(),
    originCountry = originCountry.orEmpty(),
)

fun List<ProductionCompanyResponse>.toProductionCompany(): List<ProductionCompany> = this.map {
    it.toProductionCompany()
}
