package com.onirutla.movflex.core.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.onirutla.movflex.core.databinding.ItemReviewBinding
import com.onirutla.movflex.core.domain.model.Review

class ReviewPagingAdapter(
    private inline val onItemClickListener: (View, Review) -> Unit,
) : PagingDataAdapter<Review, ReviewViewHolder>(ReviewComparator) {

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ReviewViewHolder {
        val view = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewViewHolder(view, onItemClickListener)
    }
}
