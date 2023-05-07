package com.onirutla.movflex.tv.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.domain.model.Tv

object TvSeeMoreComparator : DiffUtil.ItemCallback<SeeMore<List<Tv>>>() {
    override fun areItemsTheSame(
        oldItem: SeeMore<List<Tv>>,
        newItem: SeeMore<List<Tv>>,
    ): Boolean = oldItem.items == newItem.items

    override fun areContentsTheSame(
        oldItem: SeeMore<List<Tv>>,
        newItem: SeeMore<List<Tv>>,
    ): Boolean = oldItem == newItem
}
