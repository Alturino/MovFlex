package com.onirutla.movflex

sealed class MovFlexArg(val arg: String) {
    object MovieId : MovFlexArg(arg = "movie_id")
    object TvId : MovFlexArg(arg = "tv_id")
    object MovieSeeMoreTitle : MovFlexArg(arg = "movie_see_more_title")
}
