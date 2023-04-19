package com.onirutla.movflex.core.data.source.local.entities.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    val budget: Int,
//    val genres: List<GenreEntity>,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "imdb_id")
    val imdbId: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
//    val productionCompanies: List<ProductionCompanyEntity>,
//    val productionCountries: List<ProductionCountryResponse>,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
//    val spokenLanguages: List<SpokenLanguageResponse>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
)
