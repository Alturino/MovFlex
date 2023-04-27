package com.onirutla.movflex.core.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ItemCastVerticalBinding
import com.onirutla.movflex.core.domain.model.Cast

class CastPagingAdapter(
    private inline val onItemClickListener: (view: View, Cast) -> Unit,
) : PagingDataAdapter<Cast, CastPagingViewHolder>(CastComparator) {

    override fun onBindViewHolder(holder: CastPagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CastPagingViewHolder {
        val view = ItemCastVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CastPagingViewHolder(view, onItemClickListener)
    }
}
