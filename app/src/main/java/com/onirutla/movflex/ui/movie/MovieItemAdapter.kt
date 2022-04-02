package com.onirutla.movflex.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.databinding.MovieItemContainerBinding

class MovieItemAdapter(
    private inline val onClickListener: (view: View, movie: MovieResponse) -> Unit
) : ListAdapter<MovieResponse, MovieItemAdapter.ViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(
            oldItem: MovieResponse,
            newItem: MovieResponse
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieResponse,
            newItem: MovieResponse
        ): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: MovieResponse())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            MovieItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ViewHolder(
        private val binding: MovieItemContainerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener(it, getItem(bindingAdapterPosition) ?: MovieResponse())
            }
        }

        fun bind(movie: MovieResponse) {
            binding.movie = movie
        }
    }
}
