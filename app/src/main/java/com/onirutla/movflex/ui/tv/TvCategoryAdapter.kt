package com.onirutla.movflex.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.tv.TvResponse
import com.onirutla.movflex.databinding.CategoryContainerBinding
import com.onirutla.movflex.ui.Category

class TvCategoryAdapter(
    private inline val itemClickListener: (view: View, tv: TvResponse) -> Unit,
    private inline val seeAllClickListener: (category: Category<List<TvResponse>>) -> Unit
) : ListAdapter<Category<List<TvResponse>>, TvCategoryAdapter.ViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<Category<List<TvResponse>>>() {
        override fun areItemsTheSame(
            oldItem: Category<List<TvResponse>>,
            newItem: Category<List<TvResponse>>
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: Category<List<TvResponse>>,
            newItem: Category<List<TvResponse>>
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CategoryContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: CategoryContainerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val tvItemAdapter: TvItemAdapter by lazy {
            TvItemAdapter { view, movie -> itemClickListener(view, movie) }
        }

        private val rvViewPool = RecyclerView.RecycledViewPool()

        fun bind(category: Category<List<TvResponse>>) {
            binding.category = category
            binding.seeAll.setOnClickListener { seeAllClickListener(category) }
            tvItemAdapter.submitList(category.items)
            binding.movieGroupingList.apply {
                adapter = tvItemAdapter
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