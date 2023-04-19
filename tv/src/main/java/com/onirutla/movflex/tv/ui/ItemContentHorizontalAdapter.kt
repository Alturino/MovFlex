package com.onirutla.movflex.tv.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.onirutla.movflex.core.databinding.ItemContentHorizontalBinding

class ItemContentHorizontalAdapter(
    private inline val onClickListener: (view: View, item: Content) -> Unit,
) : ListAdapter<Content, ItemContentHorizontalViewHolder>(ContentComparator) {

    override fun onBindViewHolder(holder: ItemContentHorizontalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemContentHorizontalViewHolder {
        val view = ItemContentHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemContentHorizontalViewHolder(view, onClickListener)
    }

}

