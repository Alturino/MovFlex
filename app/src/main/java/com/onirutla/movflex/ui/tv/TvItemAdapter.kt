package com.onirutla.movflex.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.tv.TvResponse
import com.onirutla.movflex.databinding.TvItemContainerBinding

class TvItemAdapter(
    private inline val onClickListener: (view: View, movie: TvResponse) -> Unit
) : ListAdapter<TvResponse, TvItemAdapter.ViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<TvResponse>() {
        override fun areItemsTheSame(
            oldItem: TvResponse,
            newItem: TvResponse
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TvResponse,
            newItem: TvResponse
        ): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: TvResponse())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TvItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ViewHolder(
        private val binding: TvItemContainerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener(it, getItem(bindingAdapterPosition) ?: TvResponse())
            }
        }

        fun bind(tvResponse: TvResponse) {
            binding.tv = tvResponse
        }
    }
}
