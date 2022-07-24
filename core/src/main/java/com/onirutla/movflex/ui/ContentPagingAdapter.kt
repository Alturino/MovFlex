package com.onirutla.movflex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ContentPagingContainerBinding
import com.onirutla.movflex.domain.model.Content
import com.onirutla.movflex.util.Constants.ItemComparator

class ContentPagingAdapter(
    private inline val onClickListener: (view: View, itemId: Int) -> Unit
) : PagingDataAdapter<Content, ContentPagingViewHolder>(ItemComparator) {

    override fun onBindViewHolder(holder: ContentPagingViewHolder, position: Int) {
        val item = getItem(position) ?: Content()
        holder.bind(item, onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentPagingViewHolder {
        ContentPagingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).also {
            return ContentPagingViewHolder(it)
        }
    }
}
