package com.onirutla.movflex.core.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.BuildConfig
import com.onirutla.movflex.core.domain.model.Content

object Constants {
    const val TMDB_STARTING_PAGE_INDEX = 1
    const val PAGE_SIZE = 20
    const val API_TOKEN = BuildConfig.API_TOKEN
    const val BASE_URL = BuildConfig.BASE_URL
    const val BASE_IMAGE_PATH = BuildConfig.BASE_IMAGE_PATH
    const val DB_PASSPHRASE = BuildConfig.DB_PASSPRHASE

    object ItemComparator : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(
            oldItem: Content,
            newItem: Content,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content,
        ): Boolean = oldItem.title == newItem.title && oldItem.description == newItem.description
    }
}
