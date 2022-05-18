package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.databinding.MoviePagingContainerBinding
import com.onirutla.movflex.util.Constants.ItemComparator

class MoviePagingAdapter(
    private inline val onClickListener: (view: View, itemId: Int) -> Unit
) : PagingDataAdapter<ItemResponse, MoviePagingAdapter.ViewHolder>(ItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MoviePagingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: ItemResponse())
    }

    inner class ViewHolder(
        private val binding: MoviePagingContainerBinding
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