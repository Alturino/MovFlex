package com.onirutla.movflex.core.ui.review

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemReviewBinding
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.util.loadImage

class ReviewViewHolder(
    private val binding: ItemReviewBinding,
    private val onClickListener: (View, Review) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Review) {
        binding.apply {
            root.setOnClickListener { onClickListener(it, item) }
            item.apply {
                val imageUrl = if (authorDetail.avatarPath.contains("http", ignoreCase = true)) {
                    authorDetail.avatarPath.removePrefix("/")
                } else {
                    authorDetail.avatarPath
                }
                ivAvatar.loadImage(imageUrl)
                tvContent.text = content
                tvUsername.text = author
                tvPostedDate.text = createdAt
                chipRating.text = root.context.getString(
                    R.string.format_rating,
                    (authorDetail.rating.toFloat() / 2)
                )
            }
        }
    }
}

