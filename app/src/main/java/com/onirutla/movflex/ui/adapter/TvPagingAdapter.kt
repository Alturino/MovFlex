package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.databinding.TvPagingContainerBinding
import com.onirutla.movflex.util.Constants.ItemComparator

class TvPagingAdapter(
    private inline val onClickListener: (view: View, itemId: Int) -> Unit
) : PagingDataAdapter<ItemResponse, TvPagingAdapter.ViewHolder>(ItemComparator) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: ItemResponse())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TvPagingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).also {
            return ViewHolder(it)
        }
    }

    inner class ViewHolder(
        private val binding: TvPagingContainerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener(it, (getItem(absoluteAdapterPosition) ?: ItemResponse()).id)
            }
        }

        fun bind(itemResponse: ItemResponse) {
            binding.item = itemResponse
        }

    }
}