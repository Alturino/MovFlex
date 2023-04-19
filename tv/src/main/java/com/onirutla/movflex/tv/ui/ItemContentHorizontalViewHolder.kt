package com.onirutla.movflex.tv.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemContentHorizontalBinding
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH

class ItemContentHorizontalViewHolder(
    private val binding: ItemContentHorizontalBinding,
    private val onClickListener: (view: View, item: Content) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Content) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }
            content.apply {
                Glide.with(itemImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(itemImage)
                    .clearOnDetach()

                itemTitle.text = title
                itemRating.text = root.context.getString(R.string.format_rating, (voteAverage / 2))
                itemYearRelease.text = releaseDate
                itemGenre.text = genres.first().name
                itemVoteCount.text = voteCount.toString()
            }
        }
    }
}
