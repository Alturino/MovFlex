package com.onirutla.movflex.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.ItemDto
import com.onirutla.movflex.databinding.ItemPagingContainerBinding

class PagingViewHolder(
    private val binding: ItemPagingContainerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemDto: ItemDto, onClickListener: (view: View, itemId: Int) -> Unit) {
        binding.item = itemDto
        binding.root.setOnClickListener {
            onClickListener(it, itemDto.id)
        }
    }

}