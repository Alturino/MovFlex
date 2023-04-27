package com.onirutla.movflex.core.ui.cast

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.Cast

object CastComparator : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean =
        oldItem.id == newItem.id && oldItem.castId == newItem.castId && oldItem.creditId == newItem.creditId

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
        oldItem.popularity == newItem.popularity && oldItem.character == newItem.character

}
