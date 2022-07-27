package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ContentPagingContainerBinding
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.util.Constants

class ContentPagingAdapter(
    private inline val onClickListener: (view: View, content: Content) -> Unit
) : PagingDataAdapter<Content, ContentPagingViewHolder>(Constants.ItemComparator) {

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
