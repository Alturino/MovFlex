package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.databinding.ItemPagingContainerBinding
import com.onirutla.movflex.util.Constants.ItemComparator

class ItemPagingAdapter(
    private inline val onClickListener: (view: View, itemId: Int) -> Unit
) : PagingDataAdapter<ItemDto, PagingViewHolder>(ItemComparator) {

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position) ?: ItemDto()
        holder.bind(item, onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        ItemPagingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).also {
            return PagingViewHolder(it)
        }
    }
}
