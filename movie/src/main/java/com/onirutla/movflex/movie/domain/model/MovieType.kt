package com.onirutla.movflex.movie.domain.model

import androidx.annotation.StringRes
import com.onirutla.movflex.core.R

enum class MovieType(
    @StringRes val value: Int
) {
    MOVIE_UPCOMING(R.string.upcoming),
    MOVIE_TOP_RATED(R.string.top_rated),
    MOVIE_NOW_PLAYING(R.string.now_playing),
    MOVIE_POPULAR(R.string.popular),
    MOVIE_RECOMMENDATIONS(R.string.recommendations),
    MOVIE_SIMILAR(R.string.similar),
    MOVIE_REVIEWS(R.string.reviews),
    MOVIE_CASTS(R.string.casts),
}
