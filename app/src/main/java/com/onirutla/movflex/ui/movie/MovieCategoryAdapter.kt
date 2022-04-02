package com.onirutla.movflex.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.data.source.remote.response.movie.MovieResponse
import com.onirutla.movflex.databinding.CategoryContainerBinding
import com.onirutla.movflex.ui.Category

class MovieCategoryAdapter(
    private inline val itemClickListener: (view: View, movie: MovieResponse) -> Unit,
    private inline val seeAllClickListener: (category: Category<List<MovieResponse>>) -> Unit
) : ListAdapter<Category<List<MovieResponse>>, MovieCategoryAdapter.ViewHolder>(Comparator) {

    object Comparator : DiffUtil.ItemCallback<Category<List<MovieResponse>>>() {
        override fun areItemsTheSame(
            oldItem: Category<List<MovieResponse>>,
            newItem: Category<List<MovieResponse>>
        ): Boolean = oldItem.items == newItem.items

        override fun areContentsTheSame(
            oldItem: Category<List<MovieResponse>>,
            newItem: Category<List<MovieResponse>>
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

        private val movieItemAdapter: MovieItemAdapter by lazy {
            MovieItemAdapter { view, movie -> itemClickListener(view, movie) }
        }

        private val rvViewPool = RecyclerView.RecycledViewPool()

        fun bind(category: Category<List<MovieResponse>>) {
            binding.category = category
            binding.seeAll.setOnClickListener { seeAllClickListener(category) }
            movieItemAdapter.submitList(category.items)
            binding.movieGroupingList.apply {
                adapter = movieItemAdapter
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