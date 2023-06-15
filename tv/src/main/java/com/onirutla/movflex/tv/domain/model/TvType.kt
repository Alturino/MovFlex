package com.onirutla.movflex.tv.domain.model

import androidx.annotation.StringRes
import com.onirutla.movflex.tv.R
import com.onirutla.movflex.core.R as coreR

enum class TvType(@StringRes val value: Int) {
    TV_POPULAR(coreR.string.popular),
    TV_AIRING_TODAY(R.string.airing_today),
    TV_TOP_RATED(coreR.string.top_rated),
    TV_ON_THE_AIR(R.string.on_the_air),
    TV_SIMILAR(coreR.string.similar),
    TV_RECOMMENDATIONS(coreR.string.recommendations),
    TV_CASTS(coreR.string.casts),
    TV_REVIEWS(coreR.string.reviews),
}
