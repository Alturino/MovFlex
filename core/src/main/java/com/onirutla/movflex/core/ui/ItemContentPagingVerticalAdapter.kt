package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ItemContentVerticalBinding
import com.onirutla.movflex.core.domain.model.Content

class ItemContentPagingVerticalAdapter(
    private inline val onClickListener: (view: View, movieContent: Content) -> Unit,
) : PagingDataAdapter<Content, ItemContentPagingVerticalViewHolder>(ContentComparator) {

    override fun onBindViewHolder(holder: ItemContentPagingVerticalViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onClickListener)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemContentPagingVerticalViewHolder {
        val view = ItemContentVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemContentPagingVerticalViewHolder(view)
    }
}
