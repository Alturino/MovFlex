package com.onirutla.movflex.tv.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.tv.domain.model.Tv

object TvComparator : DiffUtil.ItemCallback<Tv>() {
    override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean =
        oldItem.name == newItem.name && oldItem.originalName == newItem.originalName
}
