package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.SeeMoreContainerBinding
import com.onirutla.movflex.core.domain.model.SeeMore
import com.onirutla.movflex.movie.domain.model.Movie

class MovieSeeMoreViewHolder(
    private val binding: SeeMoreContainerBinding,
    private val itemClickListener: (view: View, itemId: Int) -> Unit,
    private val seeMoreClickListener: (view: View, category: String) -> Unit,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
) : RecyclerView.ViewHolder(binding.root) {

    private val itemAdapter = MovieHorizontalAdapter { view, movie ->
        itemClickListener(view, movie.id)
    }

    fun bind(item: SeeMore<List<Movie>>) {
        binding.apply {
            tvSeeMore.setOnClickListener {
                seeMoreClickListener(it, item.title)
            }
            tvTitle.text = item.title
            itemAdapter.submitList(item.items)
            rvContent.apply {
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
