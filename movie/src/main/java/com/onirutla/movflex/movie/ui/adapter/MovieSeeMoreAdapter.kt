package com.onirutla.movflex.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.SeeMoreContainerBinding
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.domain.model.Movie

class MovieSeeMoreAdapter(
    private inline val itemClickListener: (view: View, itemId: Int) -> Unit,
    private inline val seeMoreClickListener: (view: View, category: String) -> Unit,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
) : ListAdapter<SeeMore<List<Movie>>, SeeMoreViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<SeeMore<List<Movie>>>() {
        override fun areItemsTheSame(
            oldItem: SeeMore<List<Movie>>,
            newItem: SeeMore<List<Movie>>,
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: SeeMore<List<Movie>>,
            newItem: SeeMore<List<Movie>>,
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeMoreViewHolder =
        SeeMoreViewHolder(
            binding = SeeMoreContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener = itemClickListener,
            seeMoreClickListener = seeMoreClickListener,
            rvViewPool = rvViewPool,
        )

    override fun onBindViewHolder(holder: SeeMoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
