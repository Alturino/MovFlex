package com.onirutla.movflex.movie.util

import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.data.source.remote.response.toGenre
import com.onirutla.movflex.core.data.source.remote.response.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.toSpokenLanguage
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail

fun MovieResponse.toDomain(): Movie = Movie(
    backdropPath = backdropPath.orEmpty(),
    releaseDate = releaseDate,
    genres = genreIds.map { Genre(it, "") },
    id = id,
    name = title,
    originCountry = emptyList(),
    originalLanguage = originalLanguage,
    originalName = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath.orEmpty(),
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    originalTitle = originalTitle,
    title = title,
    video = video,
)

fun List<MovieResponse>.toDomain(): List<Movie> = this.map { it.toDomain() }

fun Movie.toEntity() = MovieEntity(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    id = id,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    video = video,
    tagline = "",
    status = "",
    runtime = 0,
    revenue = 0,
    imdbId = "",
    homepage = "",
    budget = 0,
)

fun MovieEntity.toDomain() = Movie(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genres = emptyList(),
    id = id,
    name = title,
    originCountry = emptyList(),
    originalLanguage = originalLanguage,
    originalName = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    originalTitle = originalTitle,
    title = title,
    video = video,
)

fun MovieDetailResponse.toDetail() = MovieDetail(
    adult = adult,
    backdropPath = backdropPath.orEmpty(),
    belongsToCollection = belongsToCollection,
    budget = budget,
    genres = genres.toGenre(),
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath.orEmpty(),
    productionCompanies = productionCompanies.toProductionCompany(),
    productionCountries = productionCountries.toProductionCountry(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.toSpokenLanguage(),
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun MovieDetail.toEntity() = MovieEntity(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    id = id,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    budget = budget,
    homepage = homepage,
    imdbId = imdbId,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    video = video
)
