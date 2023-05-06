package com.onirutla.movflex.core.data.source.remote.response.util

import com.onirutla.movflex.core.data.source.remote.response.AuthorDetailResponse
import com.onirutla.movflex.core.data.source.remote.response.CastResponse
import com.onirutla.movflex.core.data.source.remote.response.CreatedByResponse
import com.onirutla.movflex.core.data.source.remote.response.CrewResponse
import com.onirutla.movflex.core.data.source.remote.response.EpisodeResponse
import com.onirutla.movflex.core.data.source.remote.response.GenreResponse
import com.onirutla.movflex.core.data.source.remote.response.GuestStarResponse
import com.onirutla.movflex.core.data.source.remote.response.LastEpisodeToAirResponse
import com.onirutla.movflex.core.data.source.remote.response.NetworkResponse
import com.onirutla.movflex.core.data.source.remote.response.ProductionCompanyResponse
import com.onirutla.movflex.core.data.source.remote.response.ProductionCountryResponse
import com.onirutla.movflex.core.data.source.remote.response.ReviewResponse
import com.onirutla.movflex.core.data.source.remote.response.SeasonResponse
import com.onirutla.movflex.core.data.source.remote.response.SpokenLanguageResponse
import com.onirutla.movflex.core.domain.model.AuthorDetail
import com.onirutla.movflex.core.domain.model.Cast
import com.onirutla.movflex.core.domain.model.CreatedBy
import com.onirutla.movflex.core.domain.model.Crew
import com.onirutla.movflex.core.domain.model.Episode
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.GuestStar
import com.onirutla.movflex.core.domain.model.LastEpisodeToAir
import com.onirutla.movflex.core.domain.model.Network
import com.onirutla.movflex.core.domain.model.ProductionCompany
import com.onirutla.movflex.core.domain.model.ProductionCountry
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.domain.model.Season
import com.onirutla.movflex.core.domain.model.SpokenLanguage

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

fun CreatedByResponse.toCreatedBy(): CreatedBy = CreatedBy(
    creditId = creditId.orEmpty(),
    gender = gender ?: 0,
    id = id ?: 0,
    name = name.orEmpty(),
    profilePath = profilePath.orEmpty()
)

fun List<CreatedByResponse>.toCreatedBy(): List<CreatedBy> = this.map { it.toCreatedBy() }

fun CrewResponse.toCrew() = Crew(
    adult = adult ?: false,
    creditId = creditId.orEmpty(),
    department = department.orEmpty(),
    gender = gender ?: 0,
    id = id ?: 0,
    job = job.orEmpty(),
    knownForDepartment = knownForDepartment.orEmpty(),
    name = name.orEmpty(),
    originalName = originalName.orEmpty(),
    popularity = popularity ?: 0.0,
    profilePath = profilePath.orEmpty(),
)

fun List<CrewResponse>.toCrews() = map { it.toCrew() }

fun EpisodeResponse.toEpisode() = Episode(
    airDate = airDate.orEmpty(),
    crew = crew?.toCrews().orEmpty(),
    episodeNumber = episodeNumber ?: 0,
    guestStars = guestStars?.toGuestStars().orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    overview = overview.orEmpty(),
    productionCode = productionCode.orEmpty(),
    runtime = runtime ?: 0,
    seasonNumber = seasonNumber ?: 0,
    showId = showId ?: 0,
    stillPath = stillPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)

fun List<EpisodeResponse>.toEpisodes() = map { it.toEpisode() }

fun SeasonResponse.toSeason(): Season = Season(
    airDate = airDate.orEmpty(),
    episodes = episodes?.toEpisodes().orEmpty(),
    _id = _id.orEmpty(),
    id = id ?: 0,
    name = name.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    seasonNumber = seasonNumber ?: 0,
)

fun List<SeasonResponse>.toSeasons(): List<Season> = this.map { it.toSeason() }

fun SpokenLanguageResponse.toSpokenLanguage(): SpokenLanguage = SpokenLanguage(
    englishName = englishName,
    iso6391 = iso6391,
    name = name,
)

fun List<SpokenLanguageResponse>.toSpokenLanguage(): List<SpokenLanguage> = this.map {
    it.toSpokenLanguage()
}

fun GenreResponse.toGenre(): Genre = Genre(
    id = id ?: 0,
    name = name.orEmpty(),
)

fun List<GenreResponse>.toGenre(): List<Genre> = map { it.toGenre() }

fun GuestStarResponse.toGuestStar() = GuestStar(
    adult = adult ?: false,
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

fun List<GuestStarResponse>.toGuestStars() = map { it.toGuestStar() }

fun LastEpisodeToAirResponse.toLastEpisodeToAir(): LastEpisodeToAir = LastEpisodeToAir(
    airDate = airDate.orEmpty(),
    episodeNumber = episodeNumber ?: 0,
    id = id ?: 0,
    name = name.orEmpty(),
    overview = overview.orEmpty(),
    productionCode = productionCode.orEmpty(),
    runtime = runtime ?: 0,
    seasonNumber = seasonNumber ?: 0,
    showId = showId ?: 0,
    stillPath = stillPath.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
)

fun List<LastEpisodeToAirResponse>.toLastEpisodeToAir(): List<LastEpisodeToAir> = map {
    it.toLastEpisodeToAir()
}

fun NetworkResponse.toNetwork(): Network = Network(
    id = id,
    logoPath = logoPath.orEmpty(),
    name = name.orEmpty(),
    originCountry = originCountry.orEmpty()
)

fun List<NetworkResponse>.toNetwork(): List<Network> = this.map { it.toNetwork() }

fun ProductionCompanyResponse.toProductionCompany(): ProductionCompany = ProductionCompany(
    id = id ?: 0,
    logoPath = logoPath.orEmpty(),
    name = name.orEmpty(),
    originCountry = originCountry.orEmpty(),
)

fun List<ProductionCompanyResponse>.toProductionCompany(): List<ProductionCompany> = this.map {
    it.toProductionCompany()
}

fun ProductionCountryResponse.toProductionCountry(): ProductionCountry = ProductionCountry(
    iso31661 = iso31661,
    name = name
)

fun List<ProductionCountryResponse>.toProductionCountry(): List<ProductionCountry> = this.map {
    it.toProductionCountry()
}
