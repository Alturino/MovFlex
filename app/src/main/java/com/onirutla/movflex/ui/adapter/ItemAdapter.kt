package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.databinding.ItemContainerBinding
import com.onirutla.movflex.model.Content
import com.onirutla.movflex.util.ItemComparator

class ItemAdapter(
    private inline val onClickListener: (view: View, item: Content) -> Unit
) : ListAdapter<Content, ItemAdapter.ViewHolder>(ItemComparator) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: Content())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ViewHolder(
        private val binding: ItemContainerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener(it, getItem(bindingAdapterPosition) ?: Content())
            }
        }

        fun bind(item: Content) {
            binding.item = item
        }
    }
}
