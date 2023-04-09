package com.onirutla.movflex.tv.domain.util

import com.onirutla.movflex.core.data.source.remote.response.shared.toCreatedBy
import com.onirutla.movflex.core.data.source.remote.response.shared.toGenre
import com.onirutla.movflex.core.data.source.remote.response.shared.toLastEpisodeToAir
import com.onirutla.movflex.core.data.source.remote.response.shared.toNetwork
import com.onirutla.movflex.core.data.source.remote.response.shared.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.shared.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.shared.toSeason
import com.onirutla.movflex.core.data.source.remote.response.shared.toSpokenLanguage
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.ContentDetail
import com.onirutla.movflex.core.domain.model.shared.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType
import com.onirutla.movflex.tv.core.remote.model.TvResponse
import com.onirutla.movflex.tv.core.remote.model.TvResponseDetail
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

fun TvResponseDetail.toContent(): ContentDetail = TvContentDetail(
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
