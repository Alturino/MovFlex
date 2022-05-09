package com.onirutla.movflex.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.BuildConfig
import com.onirutla.movflex.data.source.remote.response.ItemResponse

object Constants {
    const val TMDB_STARTING_PAGE_INDEX = 1
    const val NETWORK_LOAD_SIZE = 20
    const val API_TOKEN = BuildConfig.API_TOKEN
    const val BASE_URL = BuildConfig.BASE_URL
    const val BASE_IMAGE_PATH = BuildConfig.BASE_IMAGE_PATH
    const val TITLE_POPULAR = "Popular"
    const val TITLE_UPCOMING = "Upcoming"
    const val TITLE_TOP_RATED = "Top Rated"
    const val TITLE_NOW_PLAYING = "Now Playing"

    object ItemComparator : DiffUtil.ItemCallback<ItemResponse>() {
        override fun areItemsTheSame(
            oldItem: ItemResponse,
            newItem: ItemResponse
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ItemResponse,
            newItem: ItemResponse
        ): Boolean = oldItem == newItem
    }
}
