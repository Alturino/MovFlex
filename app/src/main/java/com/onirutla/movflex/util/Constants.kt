package com.onirutla.movflex.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.BuildConfig
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.data.source.remote.response.ItemResponse

object Constants {
    const val TMDB_STARTING_PAGE_INDEX = 1
    const val PAGE_SIZE = 20
    const val API_TOKEN = BuildConfig.API_TOKEN
    const val BASE_URL = BuildConfig.BASE_URL
    const val BASE_IMAGE_PATH = BuildConfig.BASE_IMAGE_PATH

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

    object FavoriteComparator : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean = oldItem == newItem
    }
}
