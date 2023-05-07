package com.onirutla.movflex.tv.ui.adapter.season

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.Season

object SeasonComparator : DiffUtil.ItemCallback<Season>() {
    override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean =
        oldItem == newItem
}
