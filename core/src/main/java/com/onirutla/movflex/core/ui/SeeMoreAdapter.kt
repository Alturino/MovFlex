package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.SeeMoreContainerBinding
import com.onirutla.movflex.core.domain.model.Content
import com.onirutla.movflex.core.domain.model.SeeMore

class SeeMoreAdapter(
    private inline val itemClickListener: (view: View, itemId: Int) -> Unit,
    private inline val seeMoreClickListener: (view: View, category: String) -> Unit,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
) : ListAdapter<SeeMore<List<Content>>, SeeMoreViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<SeeMore<List<Content>>>() {
        override fun areItemsTheSame(
            oldItem: SeeMore<List<Content>>,
            newItem: SeeMore<List<Content>>,
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: SeeMore<List<Content>>,
            newItem: SeeMore<List<Content>>,
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
