package com.onirutla.movflex.tv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.SeeMoreContainerBinding
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.tv.domain.model.Tv

class TvSeeMoreAdapter(
    private inline val itemClickListener: (view: View, itemId: Int) -> Unit,
    private inline val seeMoreClickListener: (view: View, category: String) -> Unit,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
) : ListAdapter<SeeMore<List<Tv>>, TvSeeMoreViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<SeeMore<List<Tv>>>() {
        override fun areItemsTheSame(
            oldItem: SeeMore<List<Tv>>,
            newItem: SeeMore<List<Tv>>,
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: SeeMore<List<Tv>>,
            newItem: SeeMore<List<Tv>>,
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeeMoreViewHolder =
        TvSeeMoreViewHolder(
            binding = SeeMoreContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener = itemClickListener,
            seeMoreClickListener = seeMoreClickListener,
            rvViewPool = rvViewPool,
        )

    override fun onBindViewHolder(holder: TvSeeMoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
