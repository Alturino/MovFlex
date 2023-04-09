package com.onirutla.movflex.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ItemContentHorizontalBinding
import com.onirutla.movflex.core.domain.model.Content

class ItemContentHorizontalViewHolder(
    private val binding: ItemContentHorizontalBinding,
    private val onClickListener: (view: View, item: Content) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Content) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }
            item = content
        }
    }
}
