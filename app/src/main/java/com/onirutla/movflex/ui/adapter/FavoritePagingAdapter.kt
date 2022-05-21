package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.local.entities.FavoriteEntity
import com.onirutla.movflex.databinding.FavoritePagingContainerBinding
import com.onirutla.movflex.util.Constants.FavoriteComparator

class FavoritePagingAdapter :
    PagingDataAdapter<FavoriteEntity, FavoritePagingAdapter.ViewHolder>(FavoriteComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoritePagingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: FavoriteEntity())
    }

    inner class ViewHolder(
        private val binding: FavoritePagingContainerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteResponse: FavoriteEntity) {
            binding.favorite = favoriteResponse
        }

    }
}