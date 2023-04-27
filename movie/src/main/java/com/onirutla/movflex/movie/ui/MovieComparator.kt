package com.onirutla.movflex.movie.ui

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.movie.domain.model.Movie

object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.title == newItem.title && oldItem.originalTitle == newItem.originalName && oldItem.isFavorite == newItem.isFavorite
}
