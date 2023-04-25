package com.onirutla.movflex.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemReviewBinding
import com.onirutla.movflex.core.domain.model.Review
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import timber.log.Timber

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
            item.apply {
                val imageUrl = if (authorDetail.avatarPath.contains("http", ignoreCase = true)) {
                    authorDetail.avatarPath.removePrefix("/")
                } else {
                    "${BASE_IMAGE_PATH}${authorDetail.avatarPath}"
                }
                Timber.d("imageUrl : $imageUrl")
                Glide.with(ivAvatar.context)
                    .load(imageUrl)
                    .into(ivAvatar)
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

object ReviewComparator : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
        oldItem.updatedAt == newItem.updatedAt

}
