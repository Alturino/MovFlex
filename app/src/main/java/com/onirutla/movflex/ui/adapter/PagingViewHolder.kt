package com.onirutla.movflex.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.databinding.ItemPagingContainerBinding
import com.onirutla.movflex.domain.model.Content

class PagingViewHolder(
    private val binding: ItemPagingContainerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contentDto: Content, onClickListener: (view: View, itemId: Int) -> Unit) {
        binding.item = contentDto
        binding.root.setOnClickListener {
            onClickListener(it, contentDto.id)
        }
    }

}