package com.onirutla.movflex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.ItemResponse
import com.onirutla.movflex.databinding.CategoryContainerBinding
import com.onirutla.movflex.ui.Category

class CategoryAdapter(
    private inline val itemClickListener: (view: View, itemId: Int) -> Unit,
    private inline val seeAllClickListener: (view: View, category: String) -> Unit
) : ListAdapter<Category<List<ItemResponse>>, CategoryAdapter.ViewHolder>(Comparator) {

    private val rvViewPool = RecyclerView.RecycledViewPool()

    object Comparator : DiffUtil.ItemCallback<Category<List<ItemResponse>>>() {
        override fun areItemsTheSame(
            oldItem: Category<List<ItemResponse>>,
            newItem: Category<List<ItemResponse>>
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: Category<List<ItemResponse>>,
            newItem: Category<List<ItemResponse>>
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

        private val itemAdapter by lazy {
            ItemAdapter { view, movie ->
                itemClickListener(
                    view,
                    movie.id
                )
            }
        }

        init {
            binding.seeAll.setOnClickListener {
                val category = getItem(absoluteAdapterPosition)
                seeAllClickListener(
                    it,
                    category.title
                )
            }
        }

        fun bind(category: Category<List<ItemResponse>>) {
            binding.category = category
            binding.apply {
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
            itemAdapter.submitList(category.items)
        }
    }
}