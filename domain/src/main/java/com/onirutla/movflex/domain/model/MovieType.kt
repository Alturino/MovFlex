package com.onirutla.movflex.domain.model

enum class MovieType(val value: String) {
    MOVIE_UPCOMING("Upcoming"),
    MOVIE_TOP_RATED("Top Rated"),
    MOVIE_NOW_PLAYING("Now Playing"),
    MOVIE_POPULAR("Popular")
}