package com.onirutla.movflex.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.model.Content

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