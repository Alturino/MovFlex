package com.onirutla.movflex.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ContentPagingContainerBinding
import com.onirutla.movflex.core.domain.model.Content

class ContentPagingViewHolder(
    private val binding: ContentPagingContainerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Content, onClickListener: (view: View, content: Content) -> Unit) {
        binding.content = content
        binding.root.setOnClickListener {
            onClickListener(it, content)
        }
    }

}