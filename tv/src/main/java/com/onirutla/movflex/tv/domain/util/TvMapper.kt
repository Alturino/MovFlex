package com.onirutla.movflex.tv.domain.util

import com.onirutla.movflex.core.data.source.local.entities.tv.TvEntity
import com.onirutla.movflex.core.data.source.remote.response.util.toCreatedBy
import com.onirutla.movflex.core.data.source.remote.response.util.toGenre
import com.onirutla.movflex.core.data.source.remote.response.util.toLastEpisodeToAir
import com.onirutla.movflex.core.data.source.remote.response.util.toNetwork
import com.onirutla.movflex.core.data.source.remote.response.util.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.util.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.util.toSeasons
import com.onirutla.movflex.core.data.source.remote.response.util.toSpokenLanguage
import com.onirutla.movflex.core.domain.model.LastEpisodeToAir
import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.domain.model.Tv
import com.onirutla.movflex.tv.domain.model.TvDetail

fun TvResponse.toTv() = Tv(
    backdropPath = backdropPath.orEmpty(),
    firstAirDate = firstAirDate.orEmpty(),
    genreIds = genreIds.orEmpty(),
    id = id,
    name = name.orEmpty(),
    originCountry = originCountry.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalName = originalName.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)

fun List<TvResponse>.toTvs() = this.map { it.toTv() }

fun Tv.toEntity() = TvEntity(
    adult = false,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    homepage = "",
    id = id,
    inProduction = false,
    lastAirDate = "",
    name = name,
    numberOfEpisodes = 0,
    numberOfSeasons = 0,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    status = "",
    tagline = "",
    type = "",
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TvEntity.toTv() = Tv(
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    id = id,
    originCountry = emptyList(),
    genreIds = emptyList(),
    name = name,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TvDetail.toEntity() = TvEntity(
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    homepage = homepage,
    id = id,
    inProduction = inProduction,
    lastAirDate = lastAirDate,
    name = name,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    status = status,
    tagline = tagline,
    type = type,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TvDetailResponse.toTvDetail() = TvDetail(
    adult = adult ?: false,
    backdropPath = backdropPath.orEmpty(),
    createdBy = createdBy?.toCreatedBy().orEmpty(),
    episodeRunTime = episodeRunTime.orEmpty(),
    firstAirDate = firstAirDate.orEmpty(),
    genres = genres?.toGenre()?.joinToString { it.name }.orEmpty(),
    homepage = homepage.orEmpty(),
    id = id ?: 0,
    inProduction = inProduction ?: false,
    languages = languages.orEmpty(),
    lastAirDate = lastAirDate.orEmpty(),
    lastEpisodeToAir = lastEpisodeToAir?.toLastEpisodeToAir() ?: LastEpisodeToAir(),
    name = name.orEmpty(),
    networks = networks?.toNetwork().orEmpty(),
    nextEpisodeToAir = nextEpisodeToAir.toString(),
    numberOfEpisodes = numberOfEpisodes ?: 0,
    numberOfSeasons = numberOfSeasons ?: 0,
    originCountry = originCountry.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalName = originalName.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterPath = posterPath.orEmpty(),
    productionCompanies = productionCompanies?.toProductionCompany().orEmpty(),
    productionCountries = productionCountries?.toProductionCountry().orEmpty(),
    seasons = seasons?.toSeasons().orEmpty(),
    spokenLanguages = spokenLanguages?.toSpokenLanguage().orEmpty(),
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
    type = type.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)
