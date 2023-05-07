package com.onirutla.movflex.tv.ui.adapter.episode

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.Episode


object EpisodeComparator : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
        oldItem == newItem
}
