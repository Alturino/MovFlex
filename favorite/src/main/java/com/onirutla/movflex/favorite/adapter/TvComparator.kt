package com.onirutla.movflex.favorite.adapter

import androidx.recyclerview.widget.DiffUtil

object TvComparator : DiffUtil.ItemCallback<Tv>() {
    override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean =
        oldItem.name == newItem.name && oldItem.originalName == newItem.originalName
}
