package com.onirutla.movflex.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.BuildConfig
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.domain.model.Content

object Constants {
    const val TMDB_STARTING_PAGE_INDEX = 1
    const val PAGE_SIZE = 20
    const val API_TOKEN = BuildConfig.API_TOKEN
    const val BASE_URL = BuildConfig.BASE_URL
    const val BASE_IMAGE_PATH = BuildConfig.BASE_IMAGE_PATH

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

    object ItemComparator : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(
            oldItem: Content,
            newItem: Content
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content
        ): Boolean = oldItem == newItem
    }
}
