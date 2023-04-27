package com.onirutla.movflex.core.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
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
