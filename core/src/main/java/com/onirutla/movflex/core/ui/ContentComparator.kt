package com.onirutla.movflex.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.Content

object ContentComparator : DiffUtil.ItemCallback<Content>() {
    override fun areItemsTheSame(
        oldItem: Content,
        newItem: Content,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Content,
        newItem: Content,
    ): Boolean = oldItem.name == newItem.name && oldItem.overview == newItem.overview
}
