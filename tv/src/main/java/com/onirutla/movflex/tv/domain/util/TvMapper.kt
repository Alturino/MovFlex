package com.onirutla.movflex.tv.domain.util

import com.onirutla.movflex.core.data.source.remote.response.toCreatedBy
import com.onirutla.movflex.core.data.source.remote.response.toGenre
import com.onirutla.movflex.core.data.source.remote.response.toLastEpisodeToAir
import com.onirutla.movflex.core.data.source.remote.response.toNetwork
import com.onirutla.movflex.core.data.source.remote.response.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.toSeason
import com.onirutla.movflex.core.data.source.remote.response.toSpokenLanguage
import com.onirutla.movflex.core.domain.model.ContentDetail
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvDetailResponse
import com.onirutla.movflex.tv.domain.model.TvContent
import com.onirutla.movflex.tv.domain.model.TvContentDetail

fun TvResponse.toContent(): Content = TvContent(
    backdropPath = backdropPath,
    releaseDate = firstAirDate,
    genre = Genre(id, ""),
    id = id,
    name = name,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = false,
    itemType = ItemType.Tv
)

fun TvDetailResponse.toContent(): ContentDetail = TvContentDetail(
    adult = adult,
    backdropPath = backdropPath,
    createdBy = createdBy.toCreatedBy(),
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    genres = genres.toGenre(),
    homepage = homepage,
    id = id,
    inProduction = inProduction,
    languages = languages,
    lastAirDate = lastAirDate,
    lastEpisodeToAir = lastEpisodeToAir.toLastEpisodeToAir(),
    name = name,
    networks = networks.toNetwork(),
    nextEpisodeToAir = nextEpisodeToAir,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.toProductionCompany(),
    productionCountries = productionCountries.toProductionCountry(),
    seasons = seasons.toSeason(),
    spokenLanguages = spokenLanguages.toSpokenLanguage(),
    status = status,
    tagline = tagline,
    type = type,
    voteAverage = voteAverage,
    voteCount = voteCount
)
