package com.onirutla.movflex.core.ui.review

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.Review

object ReviewComparator : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.updatedAt == newItem.updatedAt

}
