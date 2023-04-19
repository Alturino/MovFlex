package com.onirutla.movflex.movie.domain

import com.onirutla.movflex.core.data.source.remote.response.toGenre
import com.onirutla.movflex.core.data.source.remote.response.toProductionCompany
import com.onirutla.movflex.core.data.source.remote.response.toProductionCountry
import com.onirutla.movflex.core.data.source.remote.response.toSpokenLanguage
import com.onirutla.movflex.core.domain.model.Genre
import com.onirutla.movflex.core.domain.model.type.ItemType
import com.onirutla.movflex.movie.core.remote.model.MovieDetailResponse
import com.onirutla.movflex.movie.core.remote.model.MovieResponse
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.movie.domain.model.MovieDetail

fun MovieResponse.toMovie(): Movie = Movie(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genres = genreIds.map { Genre(it, "") },
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

fun Movie.toFavorite() = FavoriteEntity(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genre = genres.first().name,
    id = id.toLong(),
    name = name,
    originalLanguage = originalLanguage,
    originalName = name,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    itemType = ItemType.Movie,
    adult = adult,
    video = video,
    tagline = "",
    status = "",
    runtime = 0,
    revenue = 0,
    imdbId = "",
    homepage = "",
    budget = 0,
    title = title,
    originalTitle = originalTitle,
)

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    adult = adult,
    backdropPath = backdropPath,
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
    posterPath = posterPath,
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

fun MovieDetail.toFavorite() = FavoriteEntity(
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    genre = genres.first().name,
    id = id.toLong(),
    name = title,
    originalLanguage = originalLanguage,
    originalName = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    itemType = ItemType.Movie,
    adult = adult,
    budget = budget,
    homepage = homepage,
    imdbId = imdbId,
    originalTitle = originalTitle,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    video = video
)
