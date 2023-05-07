package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.R
import com.onirutla.movflex.core.databinding.ItemHorizontalBinding
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.domain.model.Movie

class MovieHorizontalViewHolder(
    private val binding: ItemHorizontalBinding,
    private val onClickListener: (view: View, item: Movie) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(content: Movie) {
        binding.apply {
            root.setOnClickListener {
                onClickListener(it, content)
            }
            content.apply {
                Glide.with(ivImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(ivImage)
                    .clearOnDetach()

                tvTitle.text = title
                tvRating.text = root.context.getString(R.string.format_rating, (voteAverage / 2))
                tvYearRelease.text = releaseDate
//                tvGenre.text = genres.first().name
                tvVoteCount.text = voteCount.toString()
            }
        }
    }
}
