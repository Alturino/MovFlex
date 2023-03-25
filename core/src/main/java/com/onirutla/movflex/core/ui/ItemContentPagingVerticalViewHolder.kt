package com.onirutla.movflex.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ItemContentVerticalBinding
import com.onirutla.movflex.core.domain.model.Content

class ItemContentPagingVerticalViewHolder(
    private val binding: ItemContentVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieContent: Content, onClickListener: (view: View, movieContent: Content) -> Unit) {
        binding.apply {
            content = movieContent
            root.setOnClickListener {
                onClickListener(it, movieContent)
            }
        }
    }

}
