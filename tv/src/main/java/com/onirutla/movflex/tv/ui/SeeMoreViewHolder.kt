package com.onirutla.movflex.tv.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.SeeMoreContainerBinding
import com.onirutla.movflex.core.domain.model.SeeMore

class SeeMoreViewHolder(
    private val binding: SeeMoreContainerBinding,
    private val itemClickListener: (view: View, itemId: Int) -> Unit,
    private val seeMoreClickListener: (view: View, category: String) -> Unit,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
) : RecyclerView.ViewHolder(binding.root) {

    private val itemAdapter = ItemContentHorizontalAdapter { view, movie ->
        itemClickListener(view, movie.id)
    }

    fun bind(item: SeeMore<List<Content>>) {
        binding.apply {
            seeMore = item
            seeAll.setOnClickListener {
                seeMoreClickListener(it, item.title)
            }
            itemAdapter.submitList(item.items)
            movieGroupingList.apply {
                adapter = itemAdapter
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
                setRecycledViewPool(rvViewPool)
            }
        }
    }

}
