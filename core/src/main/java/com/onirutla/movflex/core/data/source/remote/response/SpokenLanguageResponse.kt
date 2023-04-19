package com.onirutla.movflex.core.data.source.remote.response

import com.onirutla.movflex.core.domain.model.SpokenLanguage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguageResponse(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String,
)

fun SpokenLanguageResponse.toSpokenLanguage(): SpokenLanguage = SpokenLanguage(
    englishName = englishName,
    iso6391 = iso6391,
    name = name,
)

fun List<SpokenLanguageResponse>.toSpokenLanguage(): List<SpokenLanguage> = this.map {
    it.toSpokenLanguage()
}
