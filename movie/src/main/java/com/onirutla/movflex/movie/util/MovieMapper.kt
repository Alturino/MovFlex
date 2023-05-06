package com.onirutla.movflex.movie.util

import com.onirutla.movflex.core.data.source.local.entities.movie.MovieEntity
import com.onirutla.movflex.core.data.source.remote.response.util.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.util.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.util.toSpokenLanguage
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail

fun MovieResponse.toMovie(): Movie = Movie(
    backdropPath = backdropPath.orEmpty(),
    releaseDate = releaseDate,
    genres = "",
    id = id,
    name = title,
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
    isFavorite = false,
)

fun List<MovieResponse>.toMovie(): List<Movie> = this.map { it.toMovie() }

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
    isFavorite = isFavorite,
    genre = genres
)

fun Movie.toDetail() = MovieDetail(
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
    genre = "",
    productionCompanies = emptyList(),
    productionCountries = emptyList(),
    spokenLanguages = emptyList(),
    isFavorite = isFavorite,
)

fun MovieEntity.toMovie() = Movie(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genres = "",
    id = id,
    name = title,
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
    isFavorite = isFavorite,
)

fun MovieEntity.toDetail() = MovieDetail(
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genre = genre,
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = emptyList(),
    productionCountries = emptyList(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = isFavorite,
    spokenLanguages = emptyList(),
    title = title,
)

fun MovieDetailResponse.toDetail() = MovieDetail(
    adult = adult ?: false,
    backdropPath = backdropPath.orEmpty(),
    budget = budget ?: 0,
    genre = genres?.joinToString { it.name.orEmpty() }.orEmpty(),
    homepage = homepage.orEmpty(),
    id = id ?: 0,
    imdbId = imdbId.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterPath = posterPath.orEmpty(),
    productionCompanies = productionCompanies?.toProductionCompany().orEmpty(),
    productionCountries = productionCountries?.toProductionCountry().orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    spokenLanguages = spokenLanguages?.toSpokenLanguage().orEmpty(),
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    isFavorite = false
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
    video = video,
    isFavorite = isFavorite,
    genre = genre
)
