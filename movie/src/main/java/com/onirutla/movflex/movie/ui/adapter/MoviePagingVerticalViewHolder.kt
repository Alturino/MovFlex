package com.onirutla.movflex.movie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onirutla.movflex.core.databinding.ItemVerticalBinding
import com.onirutla.movflex.core.util.Constants.BASE_IMAGE_PATH
import com.onirutla.movflex.movie.domain.model.Movie
import com.onirutla.movflex.core.R as coreR

class MoviePagingVerticalViewHolder(
    private val binding: ItemVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        content: Movie,
        itemClickListener: (View, Movie) -> Unit = { _, _ -> },
    ) {
        binding.apply {
            root.setOnClickListener {
                itemClickListener(it, content)
            }

            content.apply {
                Glide.with(ivImage.context)
                    .load("$BASE_IMAGE_PATH$backdropPath")
                    .into(ivImage)
                    .clearOnDetach()

                tvRating.text = root.context
                    .getString(coreR.string.format_rating, (voteAverage.toFloat() / 2f))

                tvGenre.text = genres
                tvSynopsis.text = overview
                tvYearRelease.text = releaseDate
                tvTitle.text = title
            }
        }
    }

}
