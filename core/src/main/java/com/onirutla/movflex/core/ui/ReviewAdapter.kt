package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.databinding.ItemReviewBinding
import com.onirutla.movflex.core.domain.model.Review

class ReviewAdapter(
    private val clickListener: (View, Review) -> Unit,
) : ListAdapter<Review, ReviewViewHolder>(ReviewComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ReviewViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class ReviewViewHolder(
    private val binding: ItemReviewBinding,
    private val onClickListener: (View, Review) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Review) {
        binding.apply {
            root.setOnClickListener { onClickListener(it, item) }
            itemContent.text = item.content
            itemUsername.text = item.author
            itemPostedDated.text = item.createdAt
            chipRating.text = "${item.authorDetail.rating}"
        }
    }
}

object ReviewComparator : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.updatedAt == newItem.updatedAt

}
